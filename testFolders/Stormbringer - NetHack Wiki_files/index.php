// <source lang="JavaScript">
/*
 * MediaWiki AJAX login script.
 * This script uses jQuery UI and MediaWiki API to display an on-page login dialog
 * when the user clicks on a login link.  (Middle-clicking still works normally).
 *
 * This is still an early version, and not all bugs have been worked out yet.
 * Known bugs and issues include:
 *  - There is no way to select if you want to log in permanently or for this
 *    browser session only, it's always permanent.  The MediaWiki API doesn't
 *    currently provide any way to change that, and since the *Token cookie is
 *    HTTPOnly, it can't even be cleared by javascript.
 *  - There's no localization, and the dialog text is hardcoded (except for the
 *    site name).  We should use an API query to load the text.
 *  - There is no "e-mail new password" button.  Worked around by providing a
 *    link to the non-AJAX upload form.
 *  - Username/password autocompletion works spottily if at all.  Need to figure
 *    out how to trigger it reliably.
 */

mw.loader.using( 'jquery.ui.dialog', function () { $( function () {
    var ajaxLoginDialog;

    function doAjaxLogin ( user, pass, token ) {
        var params = {
            action : 'login',
            lgname : user,
            lgpassword : pass,
            format : 'json'
        };
        if ( token ) params.lgtoken = token;
        $.post( wgScriptPath + '/api.php', params, function ( data ) {
            var error;
            if ( data.error ) {
                error = 'An internal error occurred: ' + data.error.info;
            } else switch ( data.login.result ) {
              case 'NeedToken':
                doAjaxLogin( user, pass, data.login.token );
                return;
              case 'Success':
                // XXX: for some reason, an immediate reload sometimes shows the user still logged out, so give it half a second...
                setTimeout( function () {
                    ajaxLoginDialog.dialog('close');
                    if ( wgCanonicalNamespace == "Special" && wgCanonicalSpecialPageName == "Userlogout" ) {
                        location.href = $('#mw-returnto a:first').attr('href');
                    } else if ( wgAction == 'edit' || wgAction == 'submit' ) {
                        var button = $( $('#wikiDiff').length ? '#wpDiff' : '#wpPreview' );
                        if ( button.length ) button[0].click();
                        else location.reload(true);  // probably a protected page we couldn't edit
                    } else {
                        location.reload(true);
                    }
                }, 500 );
                return;
              case 'Illegal':
              case 'NotExists':
                error = 'The user "' + user + '" does not exist on this wiki yet.';
                ajaxLoginDialog.find('#ajaxLoginUsername').focus();
                break;
              case 'WrongPass':
              case 'WrongPluginPass':
                error = 'The password you gave was incorrect.  Please try again.';
                ajaxLoginDialog.find('#ajaxLoginPassword').val( "" ).focus();
                break;
              default:
                error = 'Unexpected login result code: ' + data.login.result;
            }
            ajaxLoginDialog.find('#ajaxLoginError').text( error );
            ajaxLoginDialog.dialog( 'option', 'disabled', false );
        }, 'json');
    }

    function ajaxLoginSubmit () {
        var user = ajaxLoginDialog.find('#ajaxLoginUsername').val();
        var pass = ajaxLoginDialog.find('#ajaxLoginPassword').val();
        if (!/[^\s_]/.test(user)) {
            ajaxLoginDialog.find('#ajaxLoginUsername').focus();
        } else if ("" == pass) {
            ajaxLoginDialog.find('#ajaxLoginPassword').focus();
        } else {
            ajaxLoginDialog.dialog( 'option', 'disabled', true );
            ajaxLoginDialog.find('#ajaxLoginError').text( "" );
            doAjaxLogin( user, pass );
        }
    }

    ajaxLoginDialog = $(
        '<form id=ajaxLoginForm><p>Don\'t have an account? <a href="' + wgScript + '?title=Special:UserLogin&type=signup" style="color:#0645AD">Create an account</a>.</p><div id=ajaxLoginError style="color:red;font-weight:bold;"></div><table><tr><th align=right><label for=ajaxLoginUsername>Username:</label></th><td><input id=ajaxLoginUsername name=wpName /></td></tr><tr><th align=right><label for=ajaxLoginPassword>Password:</label></th><td><input type=password id=ajaxLoginPassword name=wpPassword /></td></tr></table><p style="font-size:smaller">Forgot your password? <a href="' + wgScript + '?title=Special:UserLogin" style="color:#0645AD">Use this form</a>.</p></form>'
    ).dialog( {
        title : 'Log in to ' + wgSiteName,
        buttons: { 'Log in' : ajaxLoginSubmit },
        width : 400,
        autoOpen : false,
        modal: true
    } );

    ajaxLoginDialog.find('input').keyup( function (event) {
        if (event.which == 13) ajaxLoginSubmit();  // submit on enter
    } );

    window.ajaxLogin = function (event) {
        if (event) event.preventDefault();
        ajaxLoginDialog.dialog('open').find('#ajaxLoginUsername').focus();
        return false;
    };

    $('#pt-anonlogin a, a[href*="Special:UserLogin"]:not([href*="signup"], #ajaxLoginForm a)').click( window.ajaxLogin );
} ); } );

// </source>