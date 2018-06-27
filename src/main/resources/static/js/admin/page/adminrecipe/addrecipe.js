$(function() {
    var scntDiv = $('#recipeMadde');
    var i = $('#recipeMadde .row').length;

    console.log(i);

    $('#addScnt').on('click', function() {

        $('<div class="form-group row">'+
            '                                <label class="col-md-3 col-form-label" for="val-suggestions">Adım '+(i+1)+' :<span class="text-danger">*</span></label>' +
            '                                <div class="col-md-3">' +
            '                                    <textarea class="form-control" id="val-suggestions" name="recipeSteps['+i+'].description" rows="5" placeholder="Kategori tanımlamasını giriniz.." ></textarea>' +
            '                                    <a href="" class="btn button-default" class="remScnt">Sil</a> '+
            '                                    <span class="text-danger" ></span>' +
            '                                </div>' +
            '                                <div class="col-md-3"> ' +
            '                                    <input type="file" class="imgInput" name="recipeSteps['+i+'].imageFile"/>' +
            '                                  <img class="image_upload_preview" src="http://placehold.it/100x100" style="height:100px; width:100px;" alt="your image" />' +
            '                                </div>' +
            '                                <div class="col-md-3" > ' +
            '                                </div>' +
            '                            </div>').appendTo(scntDiv);
        i++;

        $('a').click(function(e) {
            e.preventDefault();
            alert("test");
            return false;
        });

        $(".imgInput").change(function() {
            alert("test");
            readUrl(this);
        });



    });

    $('a').click(function(e) {
        e.preventDefault();
        alert("test");
        return false;
    });

    $(".imgInput").change(function() {
        alert("test");
        readUrl(this);
    });

    function readUrl(input) {

        console.log(input.files[0]);
        if(input.files && input.files[0]){
            var reader = new FileReader();

            reader.onload = function (e) {
                $(input).siblings(".image_upload_preview").attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);


        }

    }

});

