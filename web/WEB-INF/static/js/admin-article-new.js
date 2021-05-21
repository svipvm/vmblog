$(function () {
    $('#article-time').val(new Date().toISOString().split('.')[0]);

    var editorOperator = new EditorOperator('editor');
    var editor = editorOperator.editor;

    $('#btn-submit').click(function () {
        var $title = $('#article-title').val();
        if ($title === "") {
            alert("请输入文章标题");
        }

        var $time = $('#article-time').val();
        var $content = editor.getMarkdown();
        $.ajax({
            url: '/admin/article/new/postArticle',
            data: {'title': $title, 'time': $time, 'content': $content},
            type: 'post',
            dataType: 'json',
            success: function (data) {
                // alert(data['result']);
                if (data['result'] === true) {
                    alert('发布成功！');
                } else {
                    alert('发布失败！');
                }
            }
        })
    });
});

function EditorOperator(name) {
    this.editor = editormd(name, {
        // width: "100%",
        height: "620px",
        // markdown: "xxxx",     // dynamic set Markdown text
        path : "/editor/lib/"  // Autoload modules mode, codemirror, marked... dependents libs path
    });
}