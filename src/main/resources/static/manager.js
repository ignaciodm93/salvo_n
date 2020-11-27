$(function() {
  // display text in the output area
  function showOutput(text) {
    $("#output").text(text);
  }
  // load and display JSON sent by server for /players
  function loadData() {
    $.get("/api/players")
    .done(function(data) {
      showOutput(JSON.stringify(data, null, 2));

    })
    .fail(function( jqXHR, textStatus ) {
      showOutput( "Failed: " + textStatus );
      console.log("fails");
    });
  }

  // handler for when user clicks add person
  function addPlayer() {
    var name = $("#name").val();
    var email = $("#email").val();
    if (name && email) {
      postPlayer(name, email);
    }
  }

  // code to post a new player using AJAX
  // on success, reload and display the updated data from the server
  function postPlayer(name, email) {
    $.post({
      headers: {
          'Content-Type': 'application/json'
      },
      dataType: "text",
      url: "/api/players",
      data: JSON.stringify({ "name": name , "email": email})
    })
    .done(function( ) {
      showOutput( "Saved -- reloading");
        console.log("works");
      loadData();
    })
    .fail(function( jqXHR, textStatus ) {
      showOutput( "Failed: " + textStatus );
    });
  }
  $("#add_player").on("click", addPlayer);
  loadData();
});