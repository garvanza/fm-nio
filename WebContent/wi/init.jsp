
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/URLEncode.js"></script>
<script type="text/javascript" src="js/jquery.capsule.js"></script>
<script type="text/javascript">
/*
OnlineClients clients= OnlineClients.instance();
OnlineClient onlineClient=null;
String ref=request.getParameter("clientReference");
int iref=-1;
boolean auth=false;
if(Utils.isInteger(ref)){
	iref=Integer.parseInt(ref);
	if(clients.has(iref)){
		onlineClient=clients.get(iref);
		
	}
	else auth=true;
}
else auth=true;
if(auth)response.sendRedirect("/auth?back=/");
String ipAddres=onlineClient.getIpAddress();
out.println("\tvar IP_ADDRESS='"+ipAddres+"';");
int clientReference=clients.add(ipAddres,request.getSession().getId());
out.println("\tvar CLIENT_REFERENCE="+iref+";");
//OnlineClient onlineClient=clients.get(clientReference);
out.println("\tvar TOKEN='"+onlineClient.getToken()+"';");
//out.println("\tvar SID='"+request.getSession().getId()+"';");
request.getSession().setMaxInactiveInterval(60*60*8);
System.out.println("CLIENT_REFERENCE="+clientReference+";\n"+"IP_ADDRESS='"+ipAddres+"';\n"+"TOKEN='"+onlineClient.getToken()+"';");
*/
</script>
<script type="text/javascript" src="js/jquery.sexytable-1.1.js"></script>
<script type="text/javascript" src="js/jquery.editable.js"></script>
<!-- 
<script type="text/javascript" src="js/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jquery.ui.position.js"></script>
<script type="text/javascript" src="js/jquery.effects.core.js"></script>
<script type="text/javascript" src="js/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="js/jquery.ui.accordion.js"></script>
 -->
<script type="text/javascript" src="js/jquery-ui-1.9.0.custom.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript" src="js/jquery.ui.autocomplete.event.js"></script>



<!--script type="text/javascript" src="js/jquery.ui.autocomplete.html.js"></script-->


<script type="text/javascript" src="js/jquery.json-2.2.js"></script>
<script type="text/javascript" src="js/jquery.editable.js"></script>
<script type="text/javascript" src="js/jquery.caret.1.02.min.js"></script>
<!--script type="text/javascript" src="js/URLEncode.js"></script>
<script type="text/javascript" src="js/jquery.capsule.js"></script-->

<script type="text/javascript" src="js/cps/SysAuth.js"></script>
<script type="text/javascript" src="cps/tableing.cps.js"></script>
<script type="text/javascript" src="cps/global.cps.js"></script>

<script type="text/javascript" src="js/jquery.idle.min.js"></script>
<script type="text/javascript" src="js/jquery-bubble-popup-v3.min.js"></script>

<script type="text/javascript" src="js/fmd/util.js"></script>
<script type="text/javascript" src="js/fmd/commandline.js"></script>
<script type="text/javascript" src="js/fmd/autocomplete.js"></script>
<script type="text/javascript" src="js/fmd/fun.js"></script>
<script type="text/javascript" src="js/fmd/auth.js"></script>


<script type="text/javascript" src="js/jspdf/jspdf.js"></script>
<script type="text/javascript"
	src="js/jspdf/jspdf.plugin.standard_fonts_metrics.js"></script>
<script type="text/javascript"
	src="js/jspdf/jspdf.plugin.split_text_to_size.js"></script>
<script type="text/javascript" src="js/jspdf/jspdf.plugin.from_html.js"></script>

<script type="text/javascript" src="js/jquery.blockUI.js"></script>



<link rel="stylesheet" type="text/css" href="css/layout.css">
<link rel="stylesheet" type="text/css"
	href="css/jquery-ui-1.8.4.cupertino.css" />
<link rel="stylesheet" type="text/css"
	href="css/jquery-bubble-popup-v3.css" />


<script type="text/javascript">
/*
$.capsule.host="http://localhost:8080/garvanza.fm.nio.nio/";
$.capsule("http://localhost:8080/garvanza.fm.nio.nio/js/cps/SysAuth.js");

$.capsule("http://localhost:8080/garvanza.fm.nio.nio/cps/tableing.cps.js");
$.capsule("http://localhost:8080/garvanza.fm.nio.nio/cps/global.cps.js");
*/


var REQUEST_NUMBER=0;

var TOKEN="${token}";
var CLIENT_REFERENCE="${clientReference}";
var SHOPMAN=${shopman};
var CONTEXT_PATH="${pageContext.request.contextPath}";
var AUTHORIZED=true;

var shopman=SHOPMAN;
var inputValue;
var products;
var productsLog=[];
var clients;
var client=new Client_("-1","PUBLICO EN GRAL",1,".","MORELIA",".","MICH",".",".","PARA FACTURAR",".",0,"");
var agent=null;
//var shopman=new setShopman("unauthorized");
var metadata=new setMetadata(1);// INVOICE_TYPE_ORDER
var requester=new setRequester(-1,"publico","publico");
var seller=new setSeller(-1,"none");
var destiny=new setDestiny(-1,"mostrador");
var commandline;
var emptyCommand=false;
var key_down = $.Event("keydown.autocomplete"); key_down.keyCode =  $.ui.keyCode.DOWN;
var keyup = $.Event("keydown.autocomplete"); keyup.keyCode =  $.ui.keyCode.UP;
var keyenter = $.Event("keydown.autocomplete"); keyenter.keyCode =  $.ui.keyCode.ENTER;
var keyenterUp = $.Event("keyup.autocomplete"); keyenterUp.keyCode =  $.ui.keyCode.ENTER;
console.log(client);

$(document).ready(function(){
	
	//$('#commands').CreateBubblePopup({innerHtml: 'comandos'});
	resetClient();
	$('#shopmanSession').html(':'+SHOPMAN.name+":"+SHOPMAN.login);
	autocomplete('#commands');
	/*$("#login-form").dialog({
        autoOpen: false,
        //height: 300,
        //width: 350,
        modal: true,
        hide: "explode",
        closeOnEscape:false,
        buttons: {
            "login": function(){
        		return clientauthenticateLP();
        	}
            
        },
        beforeClose: function(event, ui){
        	if(!AUTHORIZED)$('#login-form').dialog("option",'hide','');
		},
        close: function(){
			if(!AUTHORIZED)$('#login-form').dialog('open');
			else resetClient();
			$('#login-form').dialog("option",'hide','explode');
        }
    });*/
    $('#commands').focus();
    $( document ).idle({
		idle:1000*60*5,
		onIdle: function(){
			var doIdle=document.isIdle?false:true;
			if(doIdle)document.isIdle=true;
			else return;
			lockin();
			var passinid='passid'+$.capsule.randomString(1,15,'aA0');
			passin({
				id:passinid,
				success:function(data){
					AUTHORIZED=true;
					//alert("success " +data.authenticated+". #"+passinid+" to be removed")
					$('#'+passinid).remove();
					document.isIdle=false;
					$('#commands').focus();
				},
				message:"desbloquear "+SHOPMAN.name+"-"+SHOPMAN.login
			});
		}
	});
    /*
    var authid='authid'+$.capsule.randomString(1,15,'aA0');
	authin({
		id:authid, 
		success:function(data){
			//alert("success " +data.authenticated+". #"+authid+" to be removed");
			$('#'+authid).remove();
			AUTHORIZED=true;
			if(onlineClientHasAccess('SHOPMAN_CREATE')){
				addRegisterButton();
			}
			//$.idleTimer(1000);
			//$( document ).idleTimer( 1000 );
			//$(document).bind("idle.idleTimer", function(){
			$( document ).idle({
				idle:1000*60*5,
				onIdle: function(){
					var doIdle=document.isIdle?false:true;
					if(doIdle)document.isIdle=true;
					else return;
					lockin();
					var passinid='passid'+$.capsule.randomString(1,15,'aA0');
					passin({
						id:passinid,
						success:function(data){
							AUTHORIZED=true;
							//alert("success " +data.authenticated+". #"+passinid+" to be removed")
							$('#'+passinid).remove();
							document.isIdle=false;
							$('#commands').focus();
						},
						message:"desbloquear "+SHOPMAN
					});
				}
			});
			$('#commands').empty().focus();
		},
		message:"autenticarse"
	});
	*/
	/*
	$( "#lock-form" ).dialog({
        autoOpen: false,
        //height: 300,
        //width: 350,
        modal: true,
        hide: "explode",
        closeOnEscape:false,
        buttons: {
            "unlock": function(){return clientauthenticateP();}
        },
        beforeClose: function(event, ui){
        	if(!AUTHORIZED)$('#lock-form').dialog("option",'hide','');
		},
        close: function(){
			if(!AUTHORIZED)$('#lock-form').dialog('open');
			$('#lock-form').dialog("option",'hide','explode');
        },
        open:function(unlockMessage){
            this.title=unlockMessage;
            $('#logn').hide();
            $('#unlockpassword').focus();
        }
    });
*/
/*	$( "#register-form" ).dialog({
        autoOpen: false,
        //height: 300,
        //width: 350,
        modal: true,
        hide: "explode",

        buttons: {
            "registrar": function(){
                if(registerShopman()){
                    alert('creado');
                    $( "#register-form" ).dialog('close');
                }
                else {
                    alert('invalido');
                    $( "#register-form" ).dialog('open');
                } 
			}
        }
    });

	$( "#verify-form" ).dialog({
        autoOpen: false,
        //height: 300,
        //width: 350,
        modal: true,
        hide: "explode",

        buttons: {
            "verificar": function(){
        		if(clientauthenticateR()){
        			$( "#register-form" ).dialog('open');
                }
        		else{
        			$( "#verify-form" ).dialog('open');
            	}
            }
        }
    });


	$( "#editclient" ).dialog({
        autoOpen: false,
        height: 500,
        width: '40%',
        modal: true,
        buttons: {
            "Crear": function() {
              $('editclient-payment').trigger(keyenterUp);
            },
            Cancelar: function() {
                $( this ).dialog( "close" );
            }
        },
        close: function() {
        }
    });

	$( "#editagent" ).dialog({
        autoOpen: false,
        height: 500,
        width: '40%',
        modal: true,
        buttons: {
            "Crear": function() {
              $('editagent-payment').trigger(keyenterUp);
            },
            Cancelar: function() {
                $( this ).dialog( "close" );
            }
        },
        close: function() {
        }
    });
	*/
	$('#editproduct-6').keyup(function(event){
		if(event.keyCode==$.ui.keyCode.ENTER){
			var e1=$('#editproduct-1').val();
			var e2=$('#editproduct-2').val();
			var e3=$('#editproduct-3').val();
			var e4=$('#editproduct-4').val();
			var e5=$('#editproduct-5').val();
			var e6=$('#editproduct-6').val();
			var quantity=e1;
			var unit=e2!=''?e2:'s/u';
			var description=e3!=''?e3:'s/d';
			var code=e4!=''?e4:'s/c';
			var mark=e5!=''?e5:'s/m';
			var unitPrice=e6;
			if(unitPrice==''||!parseFloat(e6)||quantity==''||!parseFloat(quantity)){alert("precio unitario invalido y/o cantidad");return;}
			var json="{"+
				'"quantity":"'+quantity+'",'+
				'"unit":"'+unit+'",'+
				'"description":"'+description+'",'+
				'"code":"'+code+'",'+
				'"mark":"'+mark+'",'+
				'"productPriceKind":"-1",'+
				'"unitPrice":"'+unitPrice+'",'+
				'"id":"-1" }';
			productsLog.unshift($.parseJSON( json ));
			$('#editproduct').hide('slow');
			$('#commands').focus().val('');
			dolog(quantity,unit,description,code,mark,unitPrice);
			$('.tableingrow').sexytable({'editedRow':true,'index':$('.tableingrow').get(0)});
			onLogChange();
		}
	});
	/*
	$('#password').unbind('keydown').unbind('keypress').bind('keypress',function(event){
		if(event.which!=13)return;
		$( "#login-form" ).dialog("option", "buttons")['login'].apply($( "#login-form" )[0]);
	});
	$('#unlockpassword').unbind('keydown').unbind('keypress').bind('keyup',function(event){
		if(event.which!=13)return;
		$( "#lock-form" ).dialog("option", "buttons")['unlock'].apply($( "#lock-form" )[0]);
	});
	

	$( "#unauthorizedAlert" ).dialog({
        modal: true,
        stack:false,
        hide: "explode",
        autoOpen: false,
        buttons: {
            Ok: function() {
                $( this ).dialog( "close" );
            }
        }
    });

	$('#unlockpassword').focus(function(){
	    this.select();
	});
    */
	$('#lockbutton').click(function(){
		var doIdle=document.isIdle?false:true;
		if(doIdle)document.isIdle=true;
		else return;
		lockin();
		var passinid='passid'+$.capsule.randomString(1,15,'aA0');
		passin({
			id:passinid,
			success:function(data){
				AUTHORIZED=true;
				//alert("success " +data.authenticated+". #"+passinid+" to be removed")
				$('#'+passinid).remove();
				document.isIdle=false;
				$('#commands').focus();
			},
			message:"desbloquear "+SHOPMAN.name+"-"+SHOPMAN.login
		});
	});
	

	
	//authenticate();
	//resetClient();
	///INIT

	$("#log").sexytable({
		row:[
				{content:'cant',width:5},
				{content:'unidad',width:10},
				{content:'descripción',width:40},
				{content:'codigo',width:10},
				{content:'marca',width:10},
				{content:'$ unit',width:10},
				{content:"<div class='g-total'>total</div>",width:10}
				],animate:100
		});
	$("#commands").bind('autocompleteopen', function(event, ui) {
	    $(this).autocomplete( "option" , 'isOpen' , true );
	});

	$("#commands").bind('autocompleteclose', function(event, ui) {
		$(this).autocomplete( "option" , 'isOpen' , false );
	});

	$("#commands").bind('keypress',function(event){
		//console.log(event.which);
		//commandline=commandLine();
		if(event.which!=13){
			return;
		}
		//alert(event.which);
		//$('#commands').SetBubblePopupOptions({innerHtml:"comandos"});
		$('#commands').HideBubblePopup();

		//console.log(commandline.kind);
		if(commandline.kind=='product'||commandline.kind=='retrieve'){
			if(!$(this).autocomplete( "option" , 'isOpen')&&commandline.args.length==1) {
				
				REQUEST_NUMBER=REQUEST_NUMBER+1;
				$.ajax({
					url: "getproductbycode",
					dataType: "json",
					type:'POST',
					data: {
						code: commandline.args[0],
						requestNumber: REQUEST_NUMBER,
						consummerType: client?client.consummerType:1,
						token : TOKEN,
						clientReference: CLIENT_REFERENCE
					},
					success: function(data) {
						//console.log(data);
						var p=data.product;
						var quantity=commandline.quantity;
						
						dolog(quantity,p.unit,p.description,p.code,p.mark,p.unitPrice);
						productsLog.unshift(p);
						productsLog[0].quantity=quantity;
						onLogChange();
						$("#commands").val('');
					}
				});
		    }
		}
		if(commandline.kind=='incrementagentearning'){
			if(commandline.args.length<2) {
				alert("argumentos incompletos");
				return;
			}
			else if(commandline.args.length==2) {
				if(!isNumber(commandline.args[1])){
					alert("ERROR: cantidad invalida "+commandline.args[1]);
					$("#commands").val($("#commands").val().replace(commandline.args[1],""));
					return;
				}
				
				$.ajax({
					url: "invoiceport",
					dataType: "json",
					type:'POST',
					data: {
						command:commandline.command,
						args: commandline.args.join(" "),
						requestNumber: REQUEST_NUMBER,
						consummerType: client?client.consummerType:1,
						token : TOKEN,
						clientReference: CLIENT_REFERENCE
					},
					success: function(data) {
						
						invoiceInfoLog(data.invoice);
						$("#commands").val('');
						alert(data.message);
					},
					error:function(jqXHR, textStatus, errorThrown){
						console.log(jqXHR);
						console.log(textStatus);
						console.log(errorThrown);
						alert("el sistema dice: "+textStatus+" - "+errorThrown+" - "+jqXHR.responseText);
					}
					
				});
		    }
		}
		else if(commandline.kind=='editproduct'){
			$('#editproduct').show('slow');
			$('#editproduct-1').val('');
			$('#editproduct-2').val('');
			$('#editproduct-3').val('');
			$('#editproduct-4').val('');
			$('#editproduct-5').val('');
			$('#editproduct-6').val('');
			$('#editproduct-1').focus();
			$('#commands').val('');
		}
		else if(commandline.kind=='editclient'){
			/*		
			$('#editclient-consummer').val('');
			$('#editclient-consummerType').val(1);
			$('#editclient-address').val('');
			$('#editclient-interiorNumber').val('');
			$('#editclient-exteriorNumber').val('');
			$('#editclient-suburb').val('');
			$('#editclient-locality').val('MORELIA');
			$('#editclient-city').val('MORELIA');
			$('#editclient-state').val('MICHOACAN');
			$('#editclient-country').val('MEXICO');
			$('#editclient-cp').val('');
			$('#editclient-rfc').val('XAXX010101000');
			$('#editclient-tel').val('');
			$('#editclient-email').val('');
			$('#editclient-payment').val(0);
			$('#editclient').dialog("open");
			$('#editclient-consummer').focus();
			*/
			addConsummerIn('clients');
			$('commands').empty().focus();
		}
		else if(commandline.kind=='editagent'){
			/*
			$('#editagent-consummer').val('');
			$('#editagent-consummerType').val(1);
			$('#editagent-address').val('');
			$('#editagent-interiorNumber').val('');
			$('#editagent-exteriorNumber').val('');
			$('#editagent-suburb').val('');
			$('#editagent-locality').val('MORELIA');
			$('#editagent-city').val('MORELIA');
			$('#editagent-state').val('MICHOACAN');
			$('#editagent-country').val('MEXICO');
			$('#editagent-cp').val('');
			$('#editagent-rfc').val('XAXX010101000');
			$('#editagent-tel').val('');
			$('#editagent-email').val('');
			$('#editagent-payment').val(0);
			$('#editagent').dialog("open");
			$('#editagent-consummer').focus();
			*/
			addConsummerIn('agents');
			$('commands').empty().focus();
		}
		else if(commandline.kind=='sample'){
			if(agent==null||client==null){
				alert("error: Cliente y/o Agente Indefinido(s).");
				$('#commands').val('');
				return;
			}
			//alert(" kind "+commandline.kind+" argsL "+commandline.args.length);
			if(commandline.args.length>=0&&productsLog.length>0){
				/*
				if(commandline.command=='@f'){
					if(client==null){
						alert("error: cliente Indefinido.");
						$('#commands').val('');
						return;
					}
					if(!validateRfc(client.rfc)){
						$('#commands').val('');
						alert('cliente no facturable RFC invalido');
						return;
					}
				}
				if(commandline.command=='@i'||
						commandline.command=='@o'||
						commandline.command=='+o'){
					if(client==null){
						alert("error: cliente Indefinido.");
						$('#commands').val('');
						return;
					}
				}
				*/
				//alert(" kind "+commandline.kind);
				//CONFIRM
				
				$.blockUI({ message: '<h1><img src="img/wait.gif" /> esperar...</h1>' });
				$.ajax({
					type: 'POST',
					url: 'wishing',
					data: {
						client : encodeURIComponent($.toJSON(client)),
						list : encodeURIComponent($.toJSON(productsLog)),
						shopman : encodeURIComponent($.toJSON(shopman)),
						metadata : encodeURIComponent($.toJSON(metadata)),
						requester : encodeURIComponent($.toJSON(requester)),
						seller : encodeURIComponent($.toJSON(seller)),
						agent : encodeURIComponent($.toJSON(agent)),
						destiny : encodeURIComponent($.toJSON(destiny)),
						args : encodeURIComponent(commandline.args.join(" ")),
						token : TOKEN,
						command: commandline.command,
    					clientReference : CLIENT_REFERENCE
					},
					success: function(data){
						resetClient();
						$('#commands').val('');
						invoiceInfoLog(data.invoice);
						alert(data.successResponse);
						$.unblockUI();
					},
					error: function(jqXHR, textStatus, errorThrown){
						console.log(jqXHR);
						console.log(textStatus);
						console.log(errorThrown);
						alert("el sistema dice: "+textStatus+" - "+errorThrown+" - "+jqXHR.responseText);
					
						$.unblockUI();
					},
					dataType:"json"
				});
			}
			else {
				alert('ningun item en lista');
				$('#commands').val('');
			}
		}
		else if(commandline.kind=='consultthebox'){
			$.ajax({
				url: 'consultthebox',
				data: {
					command : encodeURIComponent(commandline.command),
					token : TOKEN,
					clientReference: CLIENT_REFERENCE
				},
				type:'POST',
				success: function(data){
					alert('$'+data.cash);
						$('#commands').val('');
						//window.location="init6.jsp";
					
				},
				error:function(jqXHR, textStatus, errorThrown){
					console.log(jqXHR);
					console.log(textStatus);
					console.log(errorThrown);
					alert("el sistema dice: "+textStatus+" - "+errorThrown+" - "+jqXHR.responseText);
				}/*,
				dataType:"json",
				statusCode: {
				    400: function(jqXHR, textStatus, errorThrown) {
				      alert("el sistema dice: "+textStatus+" - "+errorThrown);
				    }
				  }*/
			});
		}
		
		else if(commandline.kind=='invoicepayment'){
			/***TODO mostrar inf del agente y cliente primero, para confirmar*/
			
			if(commandline.args.length>=1){
				if(!confirm("realizar la siguiente operacion? : "+
						(commandline.command=='$d'?'pagar/abonar '+commandline.args[0].toUpperCase():'pagar agente para '+commandline.args[0].toUpperCase()))){
					$('#commands').val('');
					return;
				}
				$.ajax({
					url: 'invoicepayment',
					data: {
						command : encodeURIComponent(commandline.command),
						args : commandline.args.join(" "),
						client : encodeURIComponent($.toJSON(client)),
						token : TOKEN,
    					clientReference: CLIENT_REFERENCE
					},
					type:'POST',
					success: function(data){
						alert(data.successResponse);
							$('#commands').val('');
							//window.location="init6.jsp";
						
					},
					error:function(jqXHR, textStatus, errorThrown){
						console.log(jqXHR);
						console.log(textStatus);
						console.log(errorThrown);
						alert("el sistema dice: "+textStatus+" - "+errorThrown+" - "+jqXHR.responseText);
					}/*,
					dataType:"json",
					statusCode: {
					    400: function(jqXHR, textStatus, errorThrown) {
					      alert("el sistema dice: "+textStatus+" - "+errorThrown);
					    }
					  }*/
				});
			}
		}
		
		else if(commandline.kind=='canceldocument'){

			if(commandline.args.length>=1){
				
				if(!confirm("cancelar documento "+ commandline.args[0]+ " ? "))return;
				$.blockUI({ message: '<h1><img src="img/wait.gif" /> cancelando...</h1>' });
				$.ajax({
					url: 'invoicecancelling',
					data: {
						command : commandline.command,
						args : commandline.args.join(" "),
						client : encodeURIComponent($.toJSON(client)),
						token : TOKEN,
    					clientReference: CLIENT_REFERENCE
					},
					success: function(data){
						alert(data.message);
						$.unblockUI();
					},
					error : function(jqXHR, textStatus, errorThrown){
						alert("el sistema dice: "+textStatus+" - "+errorThrown+" - "+jqXHR.responseText);
						$.unblockUI();
					},
					dataType:"json",
					type:'POST'
				});
				$('#commands').val('');
			}
		}
		else if(commandline.kind=='facture'){
			if(client==null){
				alert("cliente indefinido");
				$('#commands').val('');
				return;
			}
			if(!validateRfc(client.rfc)){
				$('#commands').val('');
				alert('cliente no facturable RFC invalido');
				return;
			}
			if(commandline.args.length>=1){
				
				if(!confirm("sera facturado "+ commandline.args[0].toUpperCase()+ " a "+client.consummer+" ? "))return;
				$.ajax({
					url: 'facture',
					data: {
						command : commandline.command,
						args : commandline.args.join(" "),
						client : encodeURIComponent($.toJSON(client)),
						token : TOKEN,
    					clientReference: CLIENT_REFERENCE
					},
					success: function(data){
						if(commandline.command=='+f'){
							$('#commands').val('');
							//window.location="init6.jsp";
						}
						else if(commandline.command=='@-'){
							
						}
					},
					error : function(jqXHR, textStatus, errorThrown){
						alert(textStatus+" : "+errorThrown);
					},
					dataType:"json",
					type:'POST'
				});
			}
		}
		else if(commandline.kind=='discount'){
			if(commandline.args.length>=1&&commandline.args[0].charAt(commandline.args[0].length-1)==','){
				alert("discounting "+(parseFloat(commandline.args[0])));
				for(var j=0;j<productsLog.length;j++){
					//$(".tableingrow").unbind('DOMSubtreeModified');
					
					var newPrice=Math.round(productsLog[j].unitPrice*(100-parseFloat(commandline.args[0])))/100;
					$('.unitPrice').eq(j).html(newPrice);
					productsLog[j].unitPrice=newPrice;
					
					onLogChange();
								//productsLog[j].quantity=$('.quantity').eq(j).val();
				}
				$('#commands').val('');
			}
			
		}
		else if(commandline.kind=='getinvoice'){
			if(commandline.args.length>=1){
				$.blockUI({ message: '<h1><img src="img/wait.gif" /></h1>' });
				$.ajax({
					url: 'getinvoice',
					type:'POST',
					data: {
						command : commandline.command,
						reference : commandline.args.join(" "),
						token : TOKEN,
    					clientReference: CLIENT_REFERENCE
					},
					success: function(data){
						$.unblockUI();
						if(commandline.command=='@r'){
							var itms=data.items;
							for(var i=itms.length-1; i>=0;i--){
								//itms[i].quantity=parseFloat(itms[i].quantity);
								//itms[i].product.unitPrice=parseFloat(itms[i].product.unitPrice);
								console.log(itms[i]);
								dolog(
										parseFloat(itms[i].quantity),
										itms[i].unit,
										itms[i].description,
										itms[i].code,
										itms[i].mark,
										parseFloat(itms[i].unitPrice)
								);
								//productsLog.unshift(itms[i].product);
								itms[i].quantity=parseFloat(itms[i].quantity);
								itms[i].unitPrice=parseFloat(itms[i].unitPrice);
								productsLog.unshift(itms[i]);
								productsLog[0].disabled=itms[i].disabled;
								
								if(itms[i].id==-1){
									$('#log').sexytable({'editedRow':true,'index':0});
								}
								if(itms[i].disabled){
									$('#log').sexytable({'disabledRow':true,'index':0});
								}
								//productsLog[0].quantity=parseFloat(itms[i].quantity);
								
							}
							onLogChange();
							$('#commands').val('');
						}
						if(commandline.command=='@rl'){
							console.log("DATA:");
							console.log(data);
							invoiceInfoLog(data.invoice);
							$('#commands').val('');
						}
					},
					dataType:"json"
				});
			}
		}
		else if(commandline.kind=='searchinvoices'){
			//console.log('going search: '+commandline.args.join(" "));
			if(commandline.args.length>=1){
				commandline.args[commandline.args.length-1]=commandline.args[commandline.args.length-1].replace(/\./g,'');
				$("#resultset").prepend("<img src=img/wait.gif width=70px height=70px/>");
				$.ajax({
					url: 'searchinvoices',
					type:'POST',
					data: {
						paths : encodeURIComponent(commandline.args.join(" ")),
						token : TOKEN,
    					clientReference: CLIENT_REFERENCE
					},
					success: function(data){
						$('#resultset').empty();
						//alert("hora de empezar");
						console.log("DATA");
						console.log(data);
						for(var i=0;i<data.invoices.length;i++){
							var gtotal=0;
							for(var j=0;j<data.invoices[i].items.length;j++){
								var item=data.invoices[i].items[j];
								var quantity=item.quantity;
								var unitPrice=item.unitPrice;
								data.invoices[i].items[j].total=Math.round(quantity*unitPrice*100)/100;
								gtotal+=quantity*unitPrice;
								for(var field in item){
									for(var k=0;k<commandline.args.length;k++){
										if(typeOf(item[field])=="string"){
											item[field]=item[field].replace(commandline.args[k].toUpperCase().replace(/\"/g,""),"<i style='background-color:#fbff8d'><b>"+commandline.args[k].toUpperCase().replace(/\"/g,"")+"</b></i>");
											//if(item[field].indexOf(commandline.args[k].toUpperCase())!=-1)contains=true;	
										}
									}
								}
							}
							var consummer=data.invoices[i].client.consummer;
							var consummerType=data.invoices[i].client.consummerType;
							var address=data.invoices[i].client.address;
							var city=data.invoices[i].client.city;
							var state=data.invoices[i].client.state;
							var country=data.invoices[i].client.country;
							var email=data.invoices[i].client.email;
							var cp=data.invoices[i].client.cp;
							var rfc=data.invoices[i].client.rfc;
							var payment=data.invoices[i].client.payment;
							var reference=data.invoices[i].reference;
							var date=new Date(new Number(data.invoices[i].metaData.date)).format('dd.mmm.yyyy').toUpperCase();
							var shopmanName=null;
							var shopmanLogin=null;
							var agentName=null;
							var agentAddress=null;
							var agentRfc=null;
							var agentType=null;
							if(data.invoices[i].shopman!=null){
								shopmanName=data.invoices[i].shopman.name;
								shopmanLogin=data.invoices[i].shopman.login;
							}
							if(data.invoices[i].agent!=null){
								agentName=data.invoices[i].agent.consummer;
								agentAddress=data.invoices[i].agent.address;
								agentRfc=data.invoices[i].agent.rfc;
								agentType=data.invoices[i].agent.consummerType;
							}
							var invoices=data.invoices;
							var consummerContent="<b>fecha:</b>"+date+
								" | <b>ref:</b>"+reference+
								(invoices[i].totalValue?" | <b>totalValue:</b>"+invoices[i].totalValue:"")+
								(invoices[i].agentPayment?" | <b>totalValue:</b>"+invoices[i].agentPayment:"")+
								" | <b>total:</b>"+(Math.round(gtotal*100)/100)+"<br>"+
								"<b>cliente:</b>"+consummer+
								" | <b>tipo:</b>"+consummerType+
								" | <b>credito:</b>"+payment+
								" | <b>dir:</b>"+address+
								" | <b>ciudad:</b>"+city+
								" | <b>estado:</b>"+state+
								" | <b>cp:</b>"+cp+
								" | <b>rfc:</b>"+rfc+"<br>"+
								"<b>agente nombre:</b>"+(agentName?agentName:'')+
								" | <b>agente dir:</b>"+(agentAddress?agentAddress:'')+
								" | <b>agente rfc:</b>"+(agentRfc?agentRfc:'')+
								" | <b>agente tipo:</b>"+(agentType?agentType:'')+"<br>"+
								"<b>despachó nombre:</b>"+(shopmanName?shopmanName:'')+
								" | <b>despachó login:</b>"+(shopmanLogin?shopmanLogin:'');
							for(var k=0;k<commandline.args.length;k++){
								if(!isNumber(consummer[field])){
									consummerContent=consummerContent.replace(commandline.args[k].toUpperCase().replace(/\"/g,""),"<i style='background-color:#fbff8d'><b>"+commandline.args[k].toUpperCase().replace(/\"/g,"")+"</b></i>");
									//if(item[field].indexOf(commandline.args[k].toUpperCase())!=-1)contains=true;	
								}
							}
							
							var consummerObj={content:consummerContent};
							/*
							$('#resultset').inCapsule({dclass:'box fleft', width:'100%',content:''},'garvanza.fm.nio.cps.GenericDiv')
							.capsule(data.invoices[i].items,'garvanza.fm.nio.cps.TB').addClass(i%2!=0?'odd':'even')
							.preCapsule(consummerObj,'garvanza.fm.nio.cps.GenericDiv');
							*/
							console.log("data.invoices["+i+"].items");
							console.log(data.invoices[i].items);
							$('#resultset').inGaRvanzaFmNioCpsGenericDiv({dclass:'box fleft', width:'100%',content:''})
							.gaRvanzaFmNioCpsTB(data.invoices[i].items).addClass(i%2!=0?'odd':'even')
							.preGaRvanzaFmNioCpsGenericDiv(consummerObj);
						}
						$("#resultset").append("-- FIN DE BUSQUEDA --");
						
					},
					dataType:"json",
					error:function(){
						$('#resultset').empty();
					}
				});
			}
		}
		else if(commandline.kind=='makerecord'){
			if(commandline.args.length>=1){
				commandline.args[commandline.args.length-1]=commandline.args[commandline.args.length-1].replace(/\./g,'');
				
				$.ajax({
					url: 'dbport',
					type:'POST',
					data: {
						command:commandline.kind,
						args: encodeURIComponent(commandline.args.join(" ")),
						requestNumber: REQUEST_NUMBER,
						consummerType: client?client.consummerType:1,
						token : TOKEN,
						clientReference: CLIENT_REFERENCE
					},
					success: function(data){
						//alert(data.message);
						var r=data.record;
						var pdd = new Date(r.creationTime);
					    var mm = pdd.getMonth() + 1;
					    var dd = pdd.getDate();
					    var yyyy = pdd.getFullYear();
					    var date = yyyy+'.'+mm + '.' + dd;
						var src="<div id=record-"+r.id+">"+r.id+" | "+date+" | "+r.text+"</div>";
						nodeLog(src,"#records","box fleft");
						
					},
					error : function(jqXHR, textStatus, errorThrown){
						alert("el sistema dice: "+textStatus+" - "+errorThrown+" - "+jqXHR.responseText);
					}
				});
				$('#commands').val('');
			}
			else alert("escribe algo para recordar");
		}
		else if(commandline.kind=='deactivaterecord'){
			if(commandline.args.length>=1){
				commandline.args[commandline.args.length-1]=commandline.args[commandline.args.length-1].replace(/\./g,'');
				
				$.ajax({
					url: 'dbport',
					type:'POST',
					data: {
						command:commandline.kind,
						args: commandline.args.join(" "),
						requestNumber: REQUEST_NUMBER,
						consummerType: client?client.consummerType:1,
						token : TOKEN,
						clientReference: CLIENT_REFERENCE
					},
					success: function(data){
						alert("HECHO :"+data.record.text);
						$("#record-"+data.record.id).remove();
					},
					error : function(jqXHR, textStatus, errorThrown){
						alert("el sistema dice: "+textStatus+" - "+errorThrown+" - "+jqXHR.responseText);
					}
				});
				$('#commands').val('');
			}
			else alert("escribe algo para recordar");
		}
		else if(commandline.kind=='productinventoryadd'){
			$('body').preinSysAuth({id:'sysauth'}).feedSysAuth("input",{destroyAfter:true,fun:function(pass){
				
				var ret=false;
				if(authenticate_(pass)){
					ret=true;
				
					$.ajax({
						url: 'dbport',
						type:'POST',
						data: {
							command:commandline.kind,
							args: commandline.args.join(" "),
							requestNumber: REQUEST_NUMBER,
							consummerType: client?client.consummerType:1,
							token : TOKEN,
							items : encodeURIComponent($.toJSON(productsLog)),
							clientReference: CLIENT_REFERENCE
						},
						success: function(data){
							alert("HECHO :"+data.record.text);
							$("#record-"+data.record.id).remove();
						},
						error : function(jqXHR, textStatus, errorThrown){
							alert("el sistema dice: "+textStatus+" - "+errorThrown+" - "+jqXHR.responseText);
						}
					});
	            }
				return ret;
			}});
		
			
			
			$('#commands').val('');
		}
	});

 

	$(function() {
		$("#client-accordion").accordion({
			collapsible: true,
			active: false,
			header:'p',
			autoHeight: false/*,
			change: function(event, ui) {
				var H=0;
				$('.accordion-e').each(function(){
					H+=$(this).height();
				});
				$('#accordion').animate({height:H+"px"},300);
			}*/
		
		});
		$("#agent-accordion").accordion({
			collapsible: true,
			active: false,
			header:'p',
			autoHeight: false/*,
			change: function(event, ui) {
				var H=0;
				$('.accordion-e').each(function(){
					H+=$(this).height();
				});
				$('#accordion').animate({height:H+"px"},300);
			}*/
		
		});
	});



	$('#editclient-payment').keyup(function(event){
		if(event.keyCode==$.ui.keyCode.ENTER){
			var consummer=$('#editclient-consummer').val();
			var consummerType=$('#editclient-consummerType').val();
			var address=$('#editclient-address').val();
			var interiorNumber=$('#editclient-interiorNumber').val();
			var exteriorNumber=$('#editclient-exteriorNumber').val();
			var suburb=$('#editclient-suburb').val();
			var locality=$('#editclient-locality').val();
			var city=$('#editclient-city').val();
			var state=$('#editclient-state').val();
			var country=$('#editclient-country').val();
			var cp=$('#editclient-cp').val();
			var rfc=$('#editclient-rfc').val();
			var tel=$('#editclient-tel').val();
			var email=$('#editclient-email').val();
			var payment=$('#editclient-payment').val();

			client=new Client_(null, consummer, consummerType,
					address, interiorNumber, exteriorNumber,
					suburb, locality, city, country,
					state, email, cp, rfc, tel,
					payment, null, null);
			//client=new setClient(e0?e0:'-1',e1?e1:'publico',e2?e2:'1',e3?e3:'.',e4?e4:'morelia','mexico',e5?e5:'michoacan','.',e6?e6:'.',e7?e7:'.',e8?e8:'.',e9?e9:'0',e10);
							  //(code,consummer,consummerType,address,city,country,state,email,cp,rfc,tel,payment){
			$('#vips option').eq(consummerType-1).attr('selected', 'selected');
			
			$.ajax({
				url: 'welcome',
				type:'POST',
				data: {
					client : encodeURIComponent($.toJSON(client)),
					token : TOKEN,
					clientReference: CLIENT_REFERENCE
				},
				success: function(data){
					//console.log(client.consummer +" creado");
					var id=data.id;
					for(var j=0;j<productsLog.length;j++){
						var jsonsrt="["+$.toJSON(productsLog[j])+"]";
						$.ajax({
							index : j,
							url: "getthis",
							type:'POST',
							data: {
								list:encodeURIComponent(jsonsrt),
								id:id,
								token : TOKEN,
		    					clientReference: CLIENT_REFERENCE
							},
							dataType: "json",
							error: function(jqXHR, textStatus, errorThrown){
								alert("el sistema dice: "+textStatus+" - "+errorThrown+" - "+jqXHR.responseText);
							},
							success: function(data) {
								//alert(productsLog[0].quantity+" ->"+this.index);
								var j=this.index;
								if(productsLog[j].id!="-1"){
									//alert(jsonsrt);
									//$('.quantity').eq(j).text(data[j].quantity);
									$(".tableingrow").unbind('DOMSubtreeModified');
									$('.unit').eq(j).html(data[0].unit);
									$('.description').eq(j).html(data[0].description);
									$('.code').eq(j).html(data[0].code);
									$('.mark').eq(j).html(data[0].mark);
									$('.unitPrice').eq(j).html(data[0].unitPrice);
									var quantity=productsLog[j].quantity;
									if(productsLog[j].disabled){
										productsLog[j]=data[0];
										productsLog[j].disabled=true;
									}
									else productsLog[j]=data[0];
									productsLog[j].quantity=quantity;
									onLogChange();
									//productsLog[j].quantity=$('.quantity').eq(j).val();
								}									
									//else{alert("code");}
								
							}
						});
					}
					$('#editclient').dialog('close');
					$('commands').empty().focus();
				},
				error: function(jqXHR, textStatus, errorThrown){
					alert("el sistema dice: "+textStatus+" - "+errorThrown+" - "+jqXHR.responseText);
				},
				dataType:"json"
			});
			
			//alert($.toJSON(client));
			
			
		}
	});
	$('#editagent-payment').keyup(function(event){
		if(event.keyCode==$.ui.keyCode.ENTER){
			var consummer=$('#editagent-consummer').val();
			var consummerType=$('#editagent-consummerType').val();
			var address=$('#editagent-address').val();
			var interiorNumber=$('#editagent-interiorNumber').val();
			var exteriorNumber=$('#editagent-exteriorNumber').val();
			var suburb=$('#editagent-suburb').val();
			var locality=$('#editagent-locality').val();
			var city=$('#editagent-city').val();
			var state=$('#editagent-state').val();
			var country=$('#editagent-country').val();
			var cp=$('#editagent-cp').val();
			var rfc=$('#editagent-rfc').val();
			var tel=$('#editagent-tel').val();
			var email=$('#editagent-email').val();
			var payment=$('#editagent-payment').val();

			agent=new Agent_(null, consummer, consummerType,
					address, interiorNumber, exteriorNumber,
					suburb, locality, city, country,
					state, email, cp, rfc, tel,
					payment, null, null);
			$.ajax({
				url: 'welcome',
				type:'POST',
				data: {
					client : encodeURIComponent($.toJSON(agent)),
					agent:true,
					token : TOKEN,
					clientReference: CLIENT_REFERENCE
				},
				error: function(jqXHR, textStatus, errorThrown){
					alert("el sistema dice: "+textStatus+" - "+errorThrown+" - "+jqXHR.responseText);
				},
				success: function(data) {
					$('#editagent').dialog('close');
					$('commands').empty().focus();
				}
			});
			
		}
	});
	//$('#editclient').hide();
	//$('#editagent').hide();
	$('#editproduct').hide();
	
	
	/*
	$("#vips").change(function(){
		var selectedValue = $(this).find(":selected").val();
		var autocreate=$("#autocreate").is(":checked");
		if(autocreate){
			client=new setClient(client.code,
					client.consummer,
					selectedValue,
					client.address,
					client.city,
					client.country,
					client.state,
					client.email,
					client.cp,
					client.rfc,
					client.tel,
					client.payment,
					client.email
					);
			
			$.ajax({
				url: 'welcome',
				type:'POST',
				data: {
					client : encodeURIComponent($.toJSON(client)),
					token : TOKEN,
					clientReference: CLIENT_REFERENCE
				},
				success: function(data){
					//console.log(client.consummer +" creado");
					client=data;
					
					var id=client.id;
					for(var j=0;j<productsLog.length;j++){
						var jsonsrt="["+$.toJSON(productsLog[j])+"]";
						$.ajax({
							index : j,
							url: "getthis",
							type:'POST',
							data: {
								list:encodeURIComponent(jsonsrt),
								id:id,
								token : TOKEN,
		    					clientReference: CLIENT_REFERENCE
							},
							dataType: "json",
							error: function(jqXHR, textStatus, errorThrown){
								alert(textStatus);
							},
							success: function(data) {
								//alert(productsLog[0].quantity+" ->"+this.index);
								var j=this.index;
								if(productsLog[j].id!="-1"){
									//alert(jsonsrt);
									//$('.quantity').eq(j).text(data[j].quantity);
									$(".tableingrow").unbind('DOMSubtreeModified');
									$('.unit').eq(j).html(data[0].unit);
									$('.description').eq(j).html(data[0].description);
									$('.code').eq(j).html(data[0].code);
									$('.mark').eq(j).html(data[0].mark);
									$('.unitPrice').eq(j).html(data[0].unitPrice);
									var quantity=productsLog[j].quantity;
									if(productsLog[j].disabled){
										productsLog[j]=data[0];
										productsLog[j].disabled=true;
									}
									else productsLog[j]=data[0];
									productsLog[j].quantity=quantity;
									onLogChange();
									//productsLog[j].quantity=$('.quantity').eq(j).val();
								}									
									//else{alert("code");}
								
							}
						});
					}
				},
				error : function(jqXHR, textStatus, errorThrown){
					alert("maldito error-> status :"+textStatus+". thrown : "+errorThrown);
				},
				dataType:"json"
			});
		}
		else{
			var id=client.id;
			for(var i=0;i<productsLog.length;i++){
				var jsonsrt="["+$.toJSON(productsLog[i])+"]";
				$.ajax({
					index : i,
					url: "getthis",
					type:'POST',
					data: {
						list:encodeURIComponent(jsonsrt),
						id:id,
						consummerType:selectedValue,
						token : TOKEN,
    					clientReference: CLIENT_REFERENCE
					},
					dataType: "json",
					error: function(jqXHR, textStatus, errorThrown){
						alert(textStatus);
					},
					success: function(data) {
						//alert(productsLog[0].quantity+" ->"+this.index);
						
						var j=this.index;
						//console.log(data);
						//console.log('index='+j);
						if(productsLog[j].id!="-1"){
							//alert(jsonsrt);
							//$('.quantity').eq(j).text(data[j].quantity);
							$(".tableingrow").unbind('DOMSubtreeModified');
							$('.unit').eq(j).html(data[0].unit);
							$('.description').eq(j).html(data[0].description);
							$('.code').eq(j).html(data[0].code);
							$('.mark').eq(j).html(data[0].mark);
							$('.unitPrice').eq(j).html(data[0].unitPrice);
							var quantity=productsLog[j].quantity;
							if(productsLog[j].disabled){
								productsLog[j]=data[0];
								productsLog[j].disabled=true;
							}
							else productsLog[j]=data[0];
							productsLog[j].quantity=quantity;
							onLogChange();
							//productsLog[j].quantity=$('.quantity').eq(j).val();
						}									
							//else{alert("code");}
						
					}
				});
			}
		}
		client.consummerType=selectedValue;
		$("#autocreate").removeAttr("checked");
		//
		//console.log("the value you selected: " + selectedValue);
	});*/


});

</script>


<title></title>
</head>
<body>
	<div id="shopmanSession"></div>


	<div class="ui-widget">
		<label for="comandos">com:</label> <input id="commands" />

		<!--select id="vips">
		<option value="1">vip1</option>
		<option value="2">vip2</option>
		<option value="3">vip3</option>
	</select>
	 <i>autocrear cliente</i><input name="elemento1" type="checkbox" value="1" id="autocreate"/-->
		<script type="text/javascript">
		fillCommandLine=function(text){
			$('#commands').val(text).focus();
			commandline=new commandLine();
			//console.log(commandline);
		};
	</script>
		<button type="button" id="lockbutton">lock</button>
		<button type="button" onclick="new fillCommandLine('@ic');">cotizar
			a cliente</button>
		<button type="button" onclick="new fillCommandLine('@ia');">cotizar
			a agente</button>
		<button type="button" onclick="new fillCommandLine('@oc');">credito
			a cliente</button>
		<button type="button" onclick="new fillCommandLine('@oa');">credito
			a agente</button>
		<button type="button" onclick="new fillCommandLine('$oc');">pedido
			a cliente+abono</button>
		<button type="button" onclick="new fillCommandLine('$oa');">pedido
			a agente+abono</button>
		<button type="button" onclick="new fillCommandLine('$fc');">factura
			a cliente+abono</button>
		<button type="button" onclick="new fillCommandLine('$fa');">factura
			a agente+abono</button>
		<p class="g-total2">$0</p>
		<p class="g-area-to-print">0 - 0 hojas</p>
		<div id="editproduct">
			<input id="editproduct-1" /> <input id="editproduct-2" /> <input
				id="editproduct-3" /> <input id="editproduct-4" /> <input
				id="editproduct-5" /> <input id="editproduct-6" />
		</div>

	</div>

	<div id="client-accordion" style="height: auto;">
		<p>
			<a href="#" id="client" class="accordion-e"></a>
		</p>
		<div>
			<div id="client-address" class="accordion-e"></div>
			<div id="client-city" class="accordion-e"></div>
			<div id="client-state" class="accordion-e"></div>
			<div id="client-cp" class="accordion-e"></div>
			<div id="client-rfc" class="accordion-e"></div>
			<div id="client-tel" class="accordion-e"></div>
			<div id="client-email" class="accordion-e"></div>
			<div id="client-country" class="accordion-e"></div>

		</div>
	</div>
	<div id="agent-accordion" style="height: auto;">
		<p>
			<a href="#" id="agent" class="accordion-e"></a>
		</p>
		<div>
			<div id="agent-address" class="accordion-e"></div>
			<div id="agent-city" class="accordion-e"></div>
			<div id="agent-state" class="accordion-e"></div>
			<div id="agent-cp" class="accordion-e"></div>
			<div id="agent-rfc" class="accordion-e"></div>
			<div id="agent-tel" class="accordion-e"></div>
			<div id="agent-email" class="accordion-e"></div>
			<div id="agent-country" class="accordion-e"></div>

		</div>
	</div>

	<div style="position: relative; width: 100%">
		<div id="log" style="height: 500px; width: 100%; overflow: auto;"
			class="ui-widget-content"></div>
	</div>

	<div id="resultset" style="width: 100%;" class=""></div>
	<div id="logResultset" style="width: 100%;" class=""></div>
	<div id='records'>records</div>
	<!--
<div id="login-form" title="Login">
    <form>
    <fieldset>
        <label for="name">login</label>
        <input type="text" name="login" id="login" class="text ui-widget-content ui-corner-all" />
        <label for="password">password</label>
        <input type="password"  id="password" value="" class="text ui-widget-content ui-corner-all" />
    </fieldset>
    </form>
</div>

<div id="lock-form" title="Locked">
    <form>
    <fieldset>
    	<label for="name"></label>
        <input type="text" name="logn" id="logn" class="text ui-widget-content ui-corner-all" style="visibility=hidden"/>
        <label for="unlockpassword">password</label>
        <input type="password"  id="unlockpassword" value="" class="text ui-widget-content ui-corner-all" />
    </fieldset>
    </form>
</div>

<div id="verify-form" title="password">
    <form>
    <fieldset>
    	<label for="name"></label>
        <input type="text" name="verify" id="verify-" class="text ui-widget-content ui-corner-all" style="visibility=hidden"/>
        <label for="verifypassword">password</label>
        <input type="password"  id="verifypassword" value="" class="text ui-widget-content ui-corner-all" />
    </fieldset>
    </form>
</div>

<div id="register-form" title="Locked">
    <form>
    <fieldset>
    	<label for="newUserName">nombre completo</label>
        <input type="text" name="newUserName" id="newUserName" class="text ui-widget-content ui-corner-all" style="visibility=hidden"/>
        <label for="newUserLogin">login</label>
        <input type="text" name="newUserLogin" id="newUserLogin" class="text ui-widget-content ui-corner-all" style="visibility=hidden"/>
        <label for="newUserPassword">password</label>
        <input type="password"  id="newUserPassword" value="" class="text ui-widget-content ui-corner-all" />
    	<label for="reNewUserPassword">re password</label>
        <input type="password"  id="reNewUserPassword" value="" class="text ui-widget-content ui-corner-all" />
    </fieldset>
    </form>
</div>


<div id="unauthorizedAlert" title="Acceso Denegado">
    <p>
        Escribe correctamente tu login y/o password
    </p>
</div>
	<div id="editclient" title="Crear Cliente">
		<form>
		<fieldset>
			<label for="name">Nombre<em>*</em></label>
			<input id="editclient-consummer" class="sexyinput"/>
			<div></div>
			<label for="name">Tipo<em>*</em></label>
			<input id="editclient-consummerType" class="sexyinput"/>
			<div></div>
			<label for="name">Calle<em>*</em></label>
			<input id="editclient-address" class="sexyinput"/>
			<div></div>
			<label for="name">Num Int<em>*</em></label>
			<input id="editclient-interiorNumber" class="sexyinput"/>
			<div></div>
			<label for="name">Num Ext<em>*</em></label>
			<input id="editclient-exteriorNumber" class="sexyinput"/>
			<div></div>
			<label for="name">Colonia<em>*</em></label>
			<input id="editclient-suburb" class="sexyinput"/>
			<div></div>
			<label for="name">Localidad<em>*</em></label>
			<input id="editclient-locality" class="sexyinput"/>
			<div></div>
    		<label for="name">Municipio<em>*</em></label>
      		<input id="editclient-city" class="sexyinput"/>
      		<div></div>
    		<label for="name">Estado<em>*</em></label>
      		<input id="editclient-state" class="sexyinput"/>
      		<div></div>
    		<label for="name">País<em>*</em></label>
      		<input id="editclient-country" class="sexyinput"/>
      		<div></div>
    		<label for="name">Codigo Postal<em>*</em></label>
      		<input id="editclient-cp" class="sexyinput"/>
      		<div></div>
    		<label for="name">R.F.C.<em>*</em></label>
      		<input id="editclient-rfc" class="sexyinput"/>
      		<div></div>
    		<label for="name">Tel(s)<em>*</em></label>
      		<input id="editclient-tel" class="sexyinput"/>
      		<div></div>
    		<label for="name">EMail<em>*</em></label>
      		<input id="editclient-email" class="sexyinput"/>
      		<div></div>
    		<label for="name">Crédito(días)<em>*</em></label>
      		<input id="editclient-payment" class="sexyinput"/>
      		
    	</fieldset>
    	</form>
	</div>
	
	<div id="editagent" title="Crear Cliente">
		<form>
		<fieldset>
			<label for="name">Nombre<em>*</em></label>
			<input id="editagent-consummer" class="sexyinput"/>
			<div></div>
			<label for="name">Tipo<em>*</em></label>
			<input id="editagent-consummerType" class="sexyinput"/>
			<div></div>
			<label for="name">Calle<em>*</em></label>
			<input id="editagent-address" class="sexyinput"/>
			<div></div>
			<label for="name">Num Int<em>*</em></label>
			<input id="editagent-interiorNumber" class="sexyinput"/>
			<div></div>
			<label for="name">Num Ext<em>*</em></label>
			<input id="editagent-exteriorNumber" class="sexyinput"/>
			<div></div>
			<label for="name">Colonia<em>*</em></label>
			<input id="editagent-suburb" class="sexyinput"/>
			<div></div>
			<label for="name">Localidad<em>*</em></label>
			<input id="editagent-locality" class="sexyinput"/>
			<div></div>
    		<label for="name">Municipio<em>*</em></label>
      		<input id="editagent-city" class="sexyinput"/>
      		<div></div>
    		<label for="name">Estado<em>*</em></label>
      		<input id="editagent-state" class="sexyinput"/>
      		<div></div>
    		<label for="name">País<em>*</em></label>
      		<input id="editagent-country" class="sexyinput"/>
      		<div></div>
    		<label for="name">Codigo Postal<em>*</em></label>
      		<input id="editagent-cp" class="sexyinput"/>
      		<div></div>
    		<label for="name">R.F.C.<em>*</em></label>
      		<input id="editagent-rfc" class="sexyinput"/>
      		<div></div>
    		<label for="name">Tel(s)<em>*</em></label>
      		<input id="editagent-tel" class="sexyinput"/>
      		<div></div>
    		<label for="name">EMail<em>*</em></label>
      		<input id="editagent-email" class="sexyinput"/>
      		<div></div>
    		<label for="name">Crédito(días)<em>*</em></label>
      		<input id="editagent-payment" class="sexyinput"/>
    	</fieldset>
    	</form>
	</div>
-->
</body>

</html>
