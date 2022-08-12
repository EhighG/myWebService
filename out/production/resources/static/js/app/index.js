var main = { // scope 만들기.
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () { // 등록버튼(post save화면)을 누르면
            _this.save(); // main.save()인듯? 호출
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () { // 버튼 눌려서 호출되면
        var data = { // 값을 저장하고
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({ // (웹에서의) 비동기 방식 알아보기
            type: 'POST',
            url: '/api/v1/posts', // 여기로 요청을 보낸다 --> PostsApiController.save(data)가 호출된다.
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() { // 성공하면
            alert('글이 등록되었습니다.');
            window.location.href = '/'; // index화면으로 돌아간다.
        }).fail(function (error) { // 실패하면
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(), // posts-update.mustache에서 {{post.title}}
            content: $('#content').val()
        };

        var id = $('#id').val(); // PostsApiController.update()에 인자로 들어갈 id

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id, // PostsApiController.update()
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data) // PostsUpdateResponseDto로 변환될 데이터.(postsService.update()에서 unpacking됨.)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();

