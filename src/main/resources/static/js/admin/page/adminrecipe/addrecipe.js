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
            '                                    <textarea class="form-control" id="val-suggestions" name="recipeImagesCommands['+recipeMaddeCountSlider+'].description" rows="5" placeholder="'+(recipeMaddeCountSlider+1)+'. Resim Açıklaması" ></textarea>' +
            '                                    <a href="" class="btn button-default" class="remScnt">Sil</a> '+
            '                                    <span class="text-danger" ></span>' +
            '                                </div>' +
            '                                <div class="col-md-3"> ' +
            '                                    <input type="file" class="imgInput" name="recipeImagesCommands['+recipeMaddeCountSlider+'].imageFile"/>' +
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


       $('a').click(function(e) {
            e.preventDefault();
            return false;
        });


    var recipeIngredient = $('#recipeIngredient');

    $('.level_1').on('click', spawn);


        function spawn(){
            // check level
            var level = stripLevel(this.className);
            var countOthers;
            if (level !== '') {
                if(level.length < 2){
                    countOthers = this.parentNode.querySelectorAll("[class^='level_" + level +"']").length;
                }else{
                    countOthers = $('.level_'+level).length;
                    console.log(countOthers);
                }


                var x = wrapElement(level, countOthers);
                if (level.length == 1) {
                    $(x).appendTo(recipeIngredient);
                } else {
                    //x.insertAfter(this);
                    $(this).parent().append(x);
                }
            }
        }
        // strip level
        var stripLevel = function(className) {
            var index = className.indexOf('_');
            if(index > -1) {
                return className.substr(index + 1);
            } else {
                return '';
            }
        }
        
        var stripNumber = function (level) {
            var index = level.indexOf('-');
            if(index > -1) {
                return level.substr(index + 1);
            } else {
                return '';
            }
        }


        var deleteF = function(element){
            alert("test");
            element.parentNode.remove();
            return false;
        }

        // wrapper element
        var wrapElement = function(level, number) {
            var div = $('<div style="border:3px dashed #ddd; padding :10px; margin-top: 2px;"></div>');
            if (level.length == 1) {

                // it's parent
                var input = $(' <div class="form-group row">'+
                    '<label class="col-md-3 col-form-label" for="val-suggestions">Başlık<span class="text-danger">*</span></label>' +
                        '<div class="col-md-3">' +
                        '<textarea class="form-control" id="val-suggestions" name="ingredientCommands['+(parseInt(number)-1)+'].description" rows="5" placeholder="Başlık"></textarea>'+
                        '</div>'+
                        '<div class="col-md-3">'+
                        '<a href="#" class="btn button-default remScnt">Sil</a>'+

                        '<span class="text-danger"></span>'+
                        '</div>'+
                    '</div>'

                );
                div.append(input);

                // add button
                var button = $('<button type="button" class="level_' + level + '-' + number +'">' +
                    '<i class="ti-plus">Malzeme Detay</i>'+
                    '</button>');
                button.on('click', spawn);
                div.append(button);
                div.append(''+
                    '<div class="col-md-12 level_' + level + '-' + number +'" >'+
                        '<div class="form-group row" style="border:2px dashed #ddd; padding :10px; margin-top: 2px;"'+
                        '<label class="col-md-3 col-form-label" for="val-suggestions">Malzeme<span class="text-danger">*</span></label>'+
                            '<div class="col-md-3">'+
                                '<input class="form-control" id="val-suggestions" name="ingredientCommands['+(parseInt(number)-1)+'].ingredientDetailsCommands[0].description" placeholder="Başlık" />'+
                            '</div>'+
                            '</div>'+
                        '</div>'+
                    '</div>');
                return div;

            } else {
                var element = ' ' +
                    '<div class="col-md-12 level_' + level +'" >'+
                    '<div class="form-group row" style="border:2px dashed #ddd; padding :10px; margin-top: 2px;"'+
                        '<label class="col-md-3 col-form-label" for="val-suggestions">Malzeme<span class="text-danger">*</span></label>'+
                        '<div class="col-md-3">'+
                            '<input class="form-control" id="val-suggestions" name="ingredientCommands['+(parseInt(stripNumber(level))-1)+'].ingredientDetailsCommands['+(parseInt(number)-1)+'].description" placeholder="Başlık"/>'+
                        '</div>'+
                        '<a href="#" class="btn button-default sil" onclick="deleteF()">Sil</a>'+
                    '</div>'+
                    '</div>';

                return element;
            }


        }





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

