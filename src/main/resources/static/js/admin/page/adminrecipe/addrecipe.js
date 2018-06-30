$(function() {


    $(".imgInput").on("change",function() {
        readUrl(this);
    });

    $('a').click(function(e) {
        e.preventDefault();
        return false;
    });


    var recipeMaddeSlider = $('#recipeSlider');
    var recipeMaddeCountSlider = $('#recipeSlider .row').length;

    $('#addRecipeSlider').on('click', function() {
        $('<div class="form-group row">'+
            '                                <label class="col-md-3 col-form-label" for="val-suggestions">'+(recipeMaddeCountSlider+1)+'. Resim :<span class="text-danger">*</span></label>' +
            '                                <div class="col-md-3">' +
            '                                    <textarea class="form-control" id="val-suggestions" name="recipeSteps['+recipeMaddeCountSlider+'].description" rows="5" placeholder="'+(recipeMaddeCountSlider+1)+'. Resim Açıklaması" ></textarea>' +
            '                                    <a href="" class="btn button-default" class="remScnt">Sil</a> '+
            '                                    <span class="text-danger" ></span>' +
            '                                </div>' +
            '                                <div class="col-md-3"> ' +
            '                                    <input type="file" class="imgInput" name="recipeSteps['+recipeMaddeCountSlider+'].imageFile"/>' +
            '                                  <img class="image_upload_preview" src="http://placehold.it/100x100" style="height:100px; width:100px;" alt="your image" />' +
            '                                </div>' +
            '                                <div class="col-md-3" > ' +
            '                                </div>' +
            '                            </div>').appendTo(recipeMaddeSlider);
        recipeMaddeCountSlider++;

        $('a').click(function(e) {
            e.preventDefault();
            return false;
        });

        $(".imgInput").on("change",function() {
            readUrl(this);
        });



    });


    var recipeMadde = $('#recipeMadde');
    var recipeMaddeCount = $('#recipeMadde .row').length;

    $('#addRecipeMadde').on('click', function() {

        $('<div class="form-group row">'+
            '                                <label class="col-md-3 col-form-label" for="val-suggestions">Adım '+(recipeMaddeCount+1)+' :<span class="text-danger">*</span></label>' +
            '                                <div class="col-md-3">' +
            '                                    <textarea class="form-control" id="val-suggestions" name="recipeSteps['+recipeMaddeCount+'].description" rows="5" placeholder="'+(recipeMaddeCount+1)+'. Adımı giriniz" ></textarea>' +
            '                                    <a href="" class="btn button-default" class="remScnt">Sil</a> '+
            '                                    <span class="text-danger" ></span>' +
            '                                </div>' +
            '                                <div class="col-md-3"> ' +
            '                                    <input type="file" class="imgInput" name="recipeSteps['+recipeMaddeCount+'].imageFile"/>' +
            '                                  <img class="image_upload_preview" src="http://placehold.it/100x100" style="height:100px; width:100px;" alt="your image" />' +
            '                                </div>' +
            '                                <div class="col-md-3" > ' +
            '                                </div>' +
            '                            </div>').appendTo(recipeMadde);
        recipeMaddeCount++;

        $('a').click(function(e) {
            e.preventDefault();
            return false;
        });

        $(".imgInput").change(function() {
            readUrl(this);
        });



    });




    var recipeTips = $('#recipeTips');
    var recipeTipsCount = $('#recipeTips .row').length;

    $('#addRecipeTips').on('click', function() {

        $('<div class="form-group row">'+
            '                                <label class="col-md-3 col-form-label" for="val-suggestions">Adım '+(recipeTipsCount+1)+' :<span class="text-danger">*</span></label>' +
            '                                <div class="col-md-3">' +
            '                                    <textarea class="form-control" id="val-suggestions" name="recipeTipsCommands['+recipeTipsCount+'].description" rows="5" placeholder="'+(recipeTipsCount+1)+'. Adımı giriniz" ></textarea>' +
            '                                    <a href="" class="btn button-default" class="remScnt">Sil</a> '+
            '                                    <span class="text-danger" ></span>' +
            '                                </div>' +
            '                                <div class="col-md-3" > ' +
            '                                </div>' +
            '                            </div>').appendTo(recipeTips);
        recipeTipsCount++;

       $('a').click(function(e) {
            e.preventDefault();
            return false;
        });

    });

    function readUrl(input) {

        if(input.files && input.files[0]){
            var reader = new FileReader();

            reader.onload = function (e) {
                $(input).siblings(".image_upload_preview").attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);


        }

    }

});

