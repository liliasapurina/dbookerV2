var statusColorsMap = {"Home":"Crimson", "Office":"Green"};
var curSchedule = {};

function fillSchedule(startDate, endDate) {
    $('#calendar').fullCalendar('removeEventSources');
    curSchedule = {};
    $.get(
        "/schedule/between",
        {
            start : startDate,
            end: endDate
        },
        function (data) {
            data.forEach(addItem);
        }
    );
}

function addItem(item) {
    var date = item.date;
    var seatName = item.seats[0].name;
    curSchedule[date] = seatName;
    $('#calendar').fullCalendar('addEventSource',
        {
            events: [
                {
                    title: "Office Seat: " + seatName,
                    start: date,
                    color: statusColorsMap["Office"],
                    textColor: 'white'
                }
            ]
        })
}

function showPopupForSeatsChoosing(start, end) {
    var modalContent = $('#modalContent');
    modalContent.empty();
    $.get(
        "/seat/available",
        {
            start : start.format(),
            end: end.format()
        },
        function (data) {
            data.forEach(function (item) {
                modalContent.append("<div id=\"seatsAt" + item.date + "\"></div>");
                var content = $("#seatsAt" + item.date);
                content.append("<div><h3>" + item.date + "</h3></div>");

                if (curSchedule[item.date] != undefined) {
                    content.append("<div class=\"seatsHeader\"><h4>Current seat</h4></div>");
                    content.append("<div id=\"delete" + item.date + "\" class=\"seatChoosing deleteSeat\">Delete Booking For: " + curSchedule[item.date] + "</div>");
                    $("#delete" + item.date).click(function () {
                        deleteBooking(item.date);
                        $("div").remove("#seatsAt" + item.date);
                        if(modalContent.html() === "") {
                            closeSeatsChoosingPopup();
                        }
                    });
                    content.append("<div></div>");
                }

                content.append("<div class=\"seatsHeader\"><h4>Available Seats</h4></div>");
                item.seats.forEach(function (seat) {
                    content.append("<div id=\"" + seat.name + "At" + item.date + "\" class=\"seatChoosing availableSeat\">Office Seat: " + seat.name + "</div>");
                    $("#" + seat.name + "At" + item.date).click(function () {
                        bookSeat(item.date, seat.id);
                        $("div").remove("#seatsAt" + item.date);
                        if(modalContent.html() === "") {
                            closeSeatsChoosingPopup();
                        }
                    })
                });
            });
        }
    );
    location.href = "#chooseSeats";
}

function deleteBooking(date) {
    var xhr = new XMLHttpRequest();
    xhr.open('DELETE', '/schedule/unbook', true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(date);
}

function closeSeatsChoosingPopup() {
    location.href = "#current";
    var view = $('#calendar').fullCalendar('getView');
    var start = view.start.format();
    var end = view.end.format();
    fillSchedule(start, end);
}

function bookSeat(date, seatId) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/schedule/between', true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(
        [
            {
                date: date,
                seatId: seatId
            }
        ]
    ));
}

$(document).ready(function() {
    location.href = "#current";
    $('#calendar').fullCalendar({
        selectable: true,
        selectConstraint: {
            start: $.fullCalendar.moment().subtract(1, 'days'),
            end: $.fullCalendar.moment().add(69, 'years')
        },
        firstDay: 1,
        aspectRatio: 2,
        themeSystem: 'bootstrap3',
        header: {
            left: '',
            center: 'title',
            right: 'today prev,next'
        },
        viewRender: function (view, element) {
            var start = view.start.format();
            var end = view.end.format();
            fillSchedule(start, end);
        },
        select: function (start, end, jsEvent, view) {
            showPopupForSeatsChoosing(start, end.add(-1, 'days'));
        }
    });
});