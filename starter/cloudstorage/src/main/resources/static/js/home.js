function showNoteModal(isDeleteMethod, noteId, noteTitle, noteDescription) {
    if (noteId && !isDeleteMethod) {
        $('#note-form').prepend("<Input type='hidden' name='_method' value='PUT'>");
    }

    if (isDeleteMethod) {
        $('#note-form-div').hide();
        $('#delete-note-form-div').show();
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
function showCredentialModal(isDeleteMethod, credentialId, url, username, password) {
    if (credentialId && !isDeleteMethod) {
        $('#credential-form').prepend("<Input type='hidden' name='_method' value='PUT'>");
    }

    if (isDeleteMethod) {
        $('#credential-form-div').hide();
        $('#delete-credential-form-div').show();
        $('#credentialSubmit').attr('id', 'oldCredentialSubmit');
        $('#deleteCredentialSubmit').attr('id', 'credentialSubmit');
        $('#credential-id').attr('id', 'old-credential-id');
        $('#delete-credential-id').attr('id', 'credential-id');
    }

    $('#credential-id').val(credentialId ? credentialId : '');
    $('#credential-url').val(url ? url : '');
    $('#credential-username').val(username ? username : '');
    $('#credential-password').val(password ? password : '');
    $('#credentialModal').modal('show');
}

function showUpdateCredentialModal(credentialId) {
    $.ajax({
        url: "/credential/" + credentialId
    }).then(function (data) {
        showCredentialModal(false, data.credentialId, data.url, data.username, data.password);
    });
}
