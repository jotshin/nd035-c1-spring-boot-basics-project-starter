function showNoteModal(isDeleteMethod, noteId, noteTitle, noteDescription) {
    if (noteId && !isDeleteMethod) {
        $('#note-form').prepend("<Input type='hidden' name='_method' value='PUT'>");
    }

    if (isDeleteMethod) {
        $('#note-form-div').hide();
        $('#delete-form-div').show();
        $('#noteSubmit').attr('id', 'oldNoteSubmit');
        $('#deleteNoteSubmit').attr('id', 'noteSubmit');
        $('#note-id').attr('id', 'old-note-id');
        $('#delete-note-id').attr('id', 'note-id');
    }

    $('#note-id').val(noteId ? noteId : '');
    $('#note-title').val(noteTitle ? noteTitle : '');
    $('#note-description').val(noteDescription ? noteDescription : '');

    $('#noteModel').modal('show');
}

function showDeleteFileModal(fileId) {
    $('#delete-file-id').val(fileId ? fileId : '');
    $('#deleteFileModal').modal('show');
}

// For opening the credentials modal
function showCredentialModal(credentialId, url, username, password) {
    $('#credential-id').val(credentialId ? credentialId : '');
    $('#credential-url').val(url ? url : '');
    $('#credential-username').val(username ? username : '');
    $('#credential-password').val(password ? password : '');
    $('#credentialModal').modal('show');
}
