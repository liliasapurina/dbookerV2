var statusColorsMap = {"Weekend":"Red", "Home":"dimGrey", "Illness":"Black", "Vacation":"Crimson", "Office":"Green"}

$(document).ready(function() {
    $('#calendar').fullCalendar({
        selectable: true,
        firstDay: 1,
        aspectRatio: 2,
        themeSystem: 'bootstrap3',
        header: {
            left:   'prev,next today',
            center: 'title',
            right:  'month,basicWeek'
        }
    })
    var i = 10;
    for(key in statusColorsMap) {
        ++i
        $('#calendar').fullCalendar('addEventSource',
            {
                events: [
                    {
                        title: key,
                        start: '2017-09-' + i,
                        color: statusColorsMap[key],
                        textColor: 'white'
                    }
                ]
            })
    }
});