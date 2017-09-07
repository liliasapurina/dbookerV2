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
    var content = $('#modalContent');
    content.empty();
    $.get(
        "/seat/available",
        {
            start : start.format(),
            end: end.format()
        },
        function (data) {
            data.forEach(function (item) {
                content.append("<h2>" + item.date + "</h2>");
                item.seats.forEach(function (seat) {
                    content.append(seat.name + " ");
                });
            });
        }
    );
    location.href = "#chooseSeats";
}

$(document).ready(function() {
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