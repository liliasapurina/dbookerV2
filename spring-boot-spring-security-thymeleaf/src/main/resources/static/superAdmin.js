var cities = {};
var rooms = {};

function showListOfCities() {
    var content = $('#cities');
    content.empty();
    content.append("<option data-hidden=\"true\">Choose City</option>");
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
    content.append("<option data-hidden=\"true\">Choose Room</option>");
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

function clearTable() {
    $('#availableTable').empty();
}

function returnStatus(status){
    if(status){
        return "success";
    } else {
        return "danger";
    }
}


function updateStatus(seat) {
    var xhr = new XMLHttpRequest();
    xhr.open('PUT', '/seat/update', true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(seat));
}

function showListOfSeats(roomName) {
    var content = $('#availableTable');
    content.empty();
    $.get(
        "/seat/roomId=" + rooms[roomName],
        function (data) {
            data.forEach(function (item) {
                content.append("<tr id=raw" + item.id + " class=\"" + returnStatus(item.available) + "\"><td>" + item.name + "</td>"
                    + " <td><div class=\"checkbox\"><input type=\"checkbox\" id=seat" + item.id + " value=\"\"/></div></td></tr>");

                if(item.available){
                    $("#seat" + item.id).click();
                }

                $("#seat" + item.id).click(function () {
                    var raw = $("#raw" + item.id);
                    if(raw.attr('class') == "success"){
                        raw.toggleClass('success danger');
                    } else {
                        raw.toggleClass('danger success');
                    }
                    item.available = !item.available;
                    updateStatus(item);
                })
            });
        }
    );
}

function checkAddNewSeatAvailability() {
    var selectedCity = $("#cities").find("option:selected").val();
    var selectedRoom = $("#rooms").find("option:selected").val();
    if (cities[selectedCity] && rooms[selectedRoom]) {
        $("#addNewSeat").show();
    } else {
        $("#addNewSeat").hide();
    }
}

$(document).ready(function () {
    showListOfCities();

    $("#cities").on('change', function () {
        var selectedCity = $(this).find("option:selected").val();
        if (cities[selectedCity]) {
            showListOfRooms(selectedCity);
        } else {
            clearTable();
        }
        checkAddNewSeatAvailability()
    });

    $("#rooms").on('change', function () {
        var selectedRoom = $(this).find("option:selected").val();
        if (rooms[selectedRoom]) {
            showListOfSeats(selectedRoom);
        } else {
            clearTable();
        }
        checkAddNewSeatAvailability();
    });

    $("#btnSaveNewSeat").click(function () {
        var selectedRoom = $("#rooms").find("option:selected").val();
        var roomId = rooms[selectedRoom];
        var seatName = $("#seatNameInput").val();
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/seat', true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(JSON.stringify(
            {
                name: seatName,
                roomId: roomId
            }
        ));
        $("#seatNameInput").val("");
        clearTable();
        showListOfSeats(selectedRoom);
    });

    checkAddNewSeatAvailability();
});