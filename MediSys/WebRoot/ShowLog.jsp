<!DOCTYPE html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>Log viewer</title>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
  <script>window.jQuery || document.write(unescape('%3Cscript src="http://jquery.com/jquery-wp-content/themes/jquery/js/jquery-1.10.2.min.js"%3E%3C/script%3E'))
  </script>
  <script type="text/javascript">
   var logging = false;
   var auth_flag=false;;
   var complete_data;
   function refreshLog() {
	   //window.location.reload();
	   
	 //  var person = prompt("Please enter your name", "Harry Potter");
	   //alert(person);
    if (logging) {
     $.get('log', function(data) {
		 complete_data=data;
		 var gg=data.split(',');
		 var no_of_lines=gg[1];
		// alert("the data is.."+no_of_lines.split('=')[1]+"===="+gg[0]);
		 //alert("the no line in the log is..."+no_of_lines.split('=')[1]);
      
	  $('#log').html(gg[0]);
	  $('#no_of_lines_div').html("The no lines in the log is.."+no_of_lines.split('=')[1]);
	  document.getElementById('line_data_div').style.display="block";
     });
    }
    if (logging) {
      setTimeout(function() {
       refreshLog();
      }, 5000);
    }
   }

   function toggleLogs() {
    if (logging) {
     logging = false;
	 // alert($("#log").height() / parseInt($("#log").css("line-height")));
     $("#tog").val("Start");
    } else {
     logging = true;
	/* $("#dialog").dialog();
	 alert($('#password').val());
	 alert($('#username').val());*/
	/* if($('#password').val().trim()!=null&&$('#password').val().trim()!=""&&$('#username').val().trim()!=null&&$('#username').val().trim()!=""){*/
		  $("#tog").val("Stop");
         // if($('#log_lines').val()>0)
		  refreshLog();
	//  else
		 // return false;
	/* }
else{
	
	alert("please provide username and password first");
	return false;
} */   
    }
   }
   
   function show_data_lines(){
	   logging=false;
	   var line_no=$('#log_lines').val();
	   line_no=line_no+"-";
	   alert("showing lines after.."+line_no);
	   $('#log').html("");
	   $('#log').html(complete_data.split(line_no)[1]);
	   $("#tog").val("Refresh Log");
   }
   
  </script>
  
  <style type="text/css">
  
  .scrollabletextbox {
    height:100%;
    width:75%;
    font-family: Verdana, Tahoma, Arial, Helvetica, sans-serif;
    font-size: 82%;
    overflow:scroll;
    }
  
  </style>
 </head>
 <body style="width: 100%; padding: 0; margin: 0">
 <!--
<div id="dialog" title="Basic dialog">
user name:<input type="password" size="25" id="password" />
password:<input type="text" size="25" id="username" />
<input type="button" value ="Submit"/>-->
<div id="line_data_div" style="display:none;">
<input type="text" name="log_lines" id="log_lines" value="1"/>
<input type="button" value ="Get" onclick="show_data_lines()";/>
</div>
</div>
  <input type="button" id="tog" onclick="toggleLogs();" value="Start" />
  <div id="no_of_lines_div"></div>
  <div id="div1" style="height: 500px;position:relative;">
    <div id="div2" style="max-height:100%;overflow:auto;border:1px solid red;">
        <div id="log" style="height:1500px;line-height:100%;"></div>
    </div>
</div>
  <!--
  
  <div id="log" style="width: 100%;border:1px solid #ccc;font:16px/26px Georgia, Garamond, Serif;overflow:auto; padding: 0; margin: 0"></div>
  -->
  <!--
<textarea class="scrollabletextbox" name="log" id="log" value="" disabled="true"></textarea>
  -->
  
 </body>
</html>