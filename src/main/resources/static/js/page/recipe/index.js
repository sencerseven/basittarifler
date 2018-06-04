$(document).ready(function() {


    var $form = $('#commentForm');
    var $recipeId = $('#commentRecipeId');

    if($recipeId != null){
        $form.submit(function(e){
            e.preventDefault();
            e.stopPropagation();

            var item = {};
            $.each($form.serializeArray(), function() {
                item[this.name] = this.value;
            });

            jQuery.ajax({
                type:'post',
                url: $form.attr("action") + '/' + $recipeId.val(),
                contentType : 'application/json; charset=utf-8',
                dataType : 'json',
                data:JSON.stringify(item),
                success: function (data) {

                    $("#comment").val("");
                    var yorumCount = parseInt($("#yorumlarCount").text());
                    yorumCount += 1 ;
                    $("#yorumlarCount").text(yorumCount);

                    console.log(data);
                    if(data.status == "SUCCESS"){
                        $("#commentLine").append('<li>' +
                            '<div class="avatar">'+
                            '<a href="recipe-detail.html#"><img src="/images/temp-/images/recipe-comment1.jpg" alt="avatar"/></a> ' +
                            '</div>'+
                            '<div class="comment">'+
                            '<h5><a href="recipe-detail.html#">'+data.object.usersCommand.userName+'</a></h5>'+
                            '<span class="time">(2hours ago)</span>'+
                            '<p>'+data.object.text+'</p>'+
                            '</div>' +
                            '</li>');
                    }
                }

            });
        });
    }



});