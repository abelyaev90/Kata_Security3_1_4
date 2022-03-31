/*

 $(document).ready(function (){
    $('.table.eBtn').on('click',function (event){
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (user, status){
            $('.myForm #id').val(user.id);
            $('.myForm #FirstName').val(user.FirstName);
            $('.myForm #LastName').val(user.LastName);
            $('.myForm #Age').val(user.Age);
            $('.myForm #Email').val(user.Email);
            $('.myForm #Password').val(user.Password);
            $('.myForm #Role').val(user.Role);
        });
        $('.myForm #exampleModal').modal();
    });
 });*/
/*$(document).ready(function (){
    $('.table.eBtn').on('click',function (event){
        event.preventDefault();
        console.log('Скрипт сработал')
        var href = $(this).attr('href');
        console.log('href = ' + href )

        $.get(href, function (user, status){
            console.log('Get запрос сработал')
            $('.myForm #id').val(user.id);
            console.log('id = ' + user.id )
            $('.myForm #FirstName').val(user.FirstName);
            $('.myForm #LastName').val(user.LastName);
            $('.myForm #Age').val(user.Age);
            $('.myForm #Email').val(user.Email);
            $('.myForm #Password').val(user.Password);
            $('.myForm #Role').val(user.Role);
        });
        $('.myForm #exampleModal').modal();
    });
});*/

    $(document).ready(function () {
    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        console.log('Скрипт сработал')

        const href = $(this).attr('href');
        $.get(href, function (user, status) {
            $('.editForm #id').val(user.id);
            //$('.editForm #id11').val(user.id);
            $('.editForm #First name').val(user.FirstName);
            $('.editForm #Last name').val(user.LastName);
            $('.editForm #Age').val(user.Age);
            $('.editForm #Email').val(user.Email);
            // $('.myForm #select1').val(user.roles);
        });
        $('#editModal').modal('show');
    });

    $('.table .delBtn').on('click', function (event) {
    event.preventDefault();

    const href = $(this).attr('href');
    $.get(href, function (user, status) {
    $('.deleteForm #id2').val(user.id);
    $('.deleteForm #id22').val(user.id);
    $('.deleteForm #firstname2').val(user.FirstName);
    $('.deleteForm #lastName2').val(user.LastName);
    $('.deleteForm #age2').val(user.Age);
    $('.deleteForm #email2').val(user.Email);
    // $('.myForm #select2').val(user.roles);
});
    $('#deleteModal').modal('show');
});
});
