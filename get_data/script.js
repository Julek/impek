function post() {
    console.log( $("#form").serialize() );
    
    $.ajax({
        url: "http://journeyplanner.tfl.gov.uk/user/XSLT_TRIP_REQUEST2",
        type: "POST",
        data: $("#form").serialize(),
        success: function(mes) {
            console.log(mes)
        },
        error: function(mes) {
            console.log(mes);
        }
    });
     
}