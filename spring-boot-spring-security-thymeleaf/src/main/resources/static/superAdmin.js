var cities = {};
var rooms = {};

function showListOfCities() {
    var content = $('#cities');
    content.empty();
    $.get(
        "/city",
        function (data) {
            data.forEach(function (item) {
                content.append("<option>" + item.name + "</option>");
                cities[item.name] = item.id;
            });
        }
    );
}

function showListOfRooms(cityName) {
    var content = $('#rooms');
    content.empty();
    $.get(
        "/room/cityId=" + cities[cityName],
        function (data) {
            data.forEach(function (item) {
                content.append("<option>" + item.name + "</option>");
                rooms[item.name] = item.id;
            });
        }
    );
}

function showListOfSeats(roomName) {
    var content = $('#availableTable');
    content.empty();
    $.get(
        "/seat/roomId=" + rooms[roomName],
        function (data) {
            data.forEach(function (item) {
                content.append("<tr class=\"success\"><td>" + item.name +"</td>"
                    +" <td><div class=\"checkbox\"><input type=\"checkbox\" value=\"\"/></div></td></tr>");
            });
        }
    );
}

$(document).ready(function() {
    showListOfCities();

    $("#cities").on('change', function(){
        var selected = $(this).find("option:selected").val();
        showListOfRooms(selected);
    });
    $("#rooms").on('change', function(){
        var selected = $(this).find("option:selected").val();
        showListOfSeats(selected);
    });
});