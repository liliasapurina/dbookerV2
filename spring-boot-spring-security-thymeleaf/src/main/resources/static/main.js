var statusColorsMap = {"Weekend":"Red", "Home":"dimGrey", "Illness":"Black", "Vacation":"Crimson", "Office":"Green"}

function fillSchedule(startDate, endDate) {
    $('#calendar').fullCalendar('removeEventSources');
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
    $('#calendar').fullCalendar('addEventSource',
        {
            events: [
                {
                    title: "Office seat: " + seatName,
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
                content.append("<div class=\"dateHeader\"><h2>" + item.date + "</h2></div>");
                item.seats.forEach(function (seat) {
                    content.append("<div id=\"" + seat.name + "At" + item.date + "\" class=\"availableSeat\">Office seat: " + seat.name + "</div>");
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