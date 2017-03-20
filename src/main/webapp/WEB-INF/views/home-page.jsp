<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<script type="text/javascript">
	var contextPath = '<%=request.getContextPath()%>';
</script>	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<spring:url value="/resources/css/bootstrap-min.css" var="bootstrapMin"></spring:url>
<link href="${bootstrapMin}" rel="stylesheet">
<spring:url value="/resources/css/home.css" var="homeCss"></spring:url>
<link href="${homeCss}" rel="stylesheet">

<link href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css" rel="stylesheet">
</head>
</head>
<body>
 <nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">CORE</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
         <li><a href="#"><span class="glyphicon glyphicon-th" aria-hidden="true"></span> Dashboard</a></li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Tasks <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> Overview</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> New Task</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Categories</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Contacts <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Übersicht</a></li>
            <li><a href="#">New Contact</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Categories</a></li>
          </ul>
        </li>
      <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Notes <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Overview</a></li>
            <li><a href="#">New Notiz</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Notebooks</a></li>
          </ul>
      </li>
      <li><a href="#">Accounts</a></li>
      <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Learnings <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Overview</a></li>
            <li><a href="#">New Learning</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Categories</a></li>
          </ul>
      </li>
      <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Log <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Overview</a></li>
            <li><a href="#">New Entry</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath}/getDataTable">Topics</a></li>
          </ul>
      </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-link" aria-hidden="true"></span> Links<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="http://www.google.ch" target="_blank">My Webmail</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="http://www.google.ch" target="_blank">Timelogger</a></li>
            <li><a href="http://www.cubetech.ch" target="_blank">cubetech.ch</a></li>
         </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> SETTINGS<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="http://www.fgruber.ch/" target="_blank"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> User Settings</a></li>
            <li id="#"><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Logout</a></li>
         </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<div class="row">
  <div class="col-md-12">
      <div class="col-md-12">Wel come to Home Page </div>
      
      <div>
      	<table width="100%" border="0" margin="0" padding="0" 
			class="row-border tableHeader" id="empTable">
			<thead>
				<tr>
				    <th>Record Id</th>
				    <th>Employee Id</th>
					<th>Name</th>
					<th>Salary</th>
				</tr>
			</thead>
			<tfoot>
			  <tr>
					<th>Record Id</th>
				    <th>Employee Id</th>
					<th>Name</th>
					<th>Salary</th>
			 </tr>
		   </tfoot>
			<tbody>
			</tbody>
		</table>
      
      </div>
      
      
      
  </div>
</div>

<spring:url value="/resources/js/jquery.js" var="jquery"></spring:url>
<script type="text/javascript" src="${jquery}"></script>
<spring:url value="/resources/js/bootstrap.js" var="bootstrapJs"></spring:url>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript"  src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<script src="http://datatables.net/release-datatables/extensions/ColVis/js/dataTables.colVis.js"></script>
<script src="http://jquery-datatables-column-filter.googlecode.com/svn/trunk/media/js/jquery.dataTables.columnFilter.js"></script>
<script type="text/javascript">
	var contextPath = '<%=request.getContextPath()%>';
	var path = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript">
	$('#logoutId').click(function(e){
		e.preventDefault();
	    $.ajax({
	    	method: 'GET',
            url:  "${pageContext.request.contextPath}/logout",
          	
          	/* async: true,               
               data: "typeValue="+typeValue,
            */  
            success: function(jsonResponse){
            	  console.log('logout succesfully...');    
            },
            error: function(){
                console.log('failed to logout... ');
            }
            
	    });
		
	});

	
	var table;
	jQuery(document).ready(function() {
		table = $('#empTable').dataTable({
			  	"bPaginate": true,
			  	"order": [ 0, 'asc' ],
			  	"bInfo": true,
			  	"iDisplayStart":0,
			  	"bProcessing" : true,
			 	"bServerSide" : true,
			 	"sAjaxSource" : path+"/getDynamicTableData",
			 	"sServerMethod": "POST",
			 	"dom": 'C<"clear">lfrtip',
				colVis: {
					"align": "right",
		            restore: "Restore",
		            showAll: "Show all",
		            showNone: "Show none",
					order: 'alpha',
					"buttonText": "columns <img src=\"/datatableServersideExample/images/caaret.png\"/>"
		        },
			    "language": {
		            "infoFiltered": ""
		        },
		        "dom": 'Cf<"toolbar"">rtip',
		
		      })
				  .columnFilter({
					  aoColumns: [ 
						             { type: "number"},
							         { type: "text" },
							         { type: "text" },
							         { type: "text" },
			                         { type: "text" },
			                         { type: "text" },
								],
							bUseColVis: true
				   }).fnSetFilteringDelay();
				$("#personTable_length").hide();
				$("div.toolbar").append('<div class="btn-group" style="padding:5px "><button class="btn btn-default" id="refreshbtn" style="background:none;border:1px solid #ccc;height:30px" type="button"><span class="glyphicon glyphicon-refresh" style="padding:3px"></span></button></div>');
				   $("div.toolbar").css("float","right");
				   $('#refreshbtn').click(function(){
					   table.fnStandingRedraw();
			   });
				
		});
	
	jQuery.fn.dataTableExt.oApi.fnSetFilteringDelay = function (oSettings, iDelay) {
	    var _that = this;
	    if ( iDelay === undefined ) {
	        iDelay = 250;
	    }
	       
	    this.each( function ( i ) {
	        $.fn.dataTableExt.iApiIndex = i;
	        var
	            $this = this,
	            oTimerId = null,
	            sPreviousSearch = null,
	            anControl = $( 'input', _that );
	          //    console.log(anControl.length + " Controls Found");
	            anControl.unbind( 'keyup' ).bind( 'keyup', function() {
	                var $$this = $this;
	                var value = this.value;
	                var index = $( 'input', _that ).index($(this));
	            //    console.log("Running Keyup " +  this.value + " index " + $( 'input', _that ).index($(this)));
	                 
	                if (oTimerId != null) {
	                //  console.log("clear timer...");
	                     window.clearTimeout(oTimerId);
	                }
	                 
	                oTimerId = window.setTimeout(function() {
	                    $.fn.dataTableExt.iApiIndex = i;
	                    // console.log("running filter " + value + " index " + $( 'input', _that ).index($(this)));
	                    _that.fnFilter( value,  index);
	                 //   console.log("done running filter");
	                }, iDelay);
	        });
	           
	        return this;
	    } );
	    return this;
	};
	
	(function($){
	    $.fn.dataTableExt.oApi.fnStandingRedraw = function(oSettings) {
	        if(oSettings.oFeatures.bServerSide === false){
	            var before = oSettings._iDisplayStart;
	            oSettings.oApi._fnReDraw(oSettings);
	            //iDisplayStart has been reset to zero - so lets change it back
	            oSettings._iDisplayStart = before;
	            oSettings.oApi._fnCalculateEnd(oSettings);
	        }
	         
	        //draw the 'current' page
	        oSettings.oApi._fnDraw(oSettings);
	    };
	})(jQuery);
</script>
</body>
</html>