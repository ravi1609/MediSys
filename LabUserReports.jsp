<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>Reports Names Addition Page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%-- <script src="${pageContext.request.contextPath}/js/textvalidation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-ui-1.8.18.custom.min.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/jquery/css/redmond/jquery-ui-1.8.18.custom.css" /> --%>

<script type="text/javascript">
/* 
function validateForm(){

       
       

           if( document.add_report_name_form.report_name.value=="")
		      {  showAlert("* Enter Value For Report Name....");
		 
		      document.add_report_name_form.report_name.focus();
		 
		      document.getElementById('report_name').style.backgroundColor = "#FFEEEE";
		
		      return false;
		      }


           if( document.add_report_name_form.report_xml_name.value=="")
		      {     showAlert("* Enter Value For Report Xml Name....");
		        document.add_report_name_form.report_xml_name.focus();
		 
		      document.getElementById('report_xml_name').style.backgroundColor = "#FFEEEE";
	
		      return false;
		      }



             if( document.add_report_name_form.report_desc.value=="")
		      {
		        showAlert("* Enter Value For Report Description....");
		   
		      document.add_report_name_form.report_desc.focus();
		      document.getElementById('report_desc').style.backgroundColor = "#FFEEEE";
		      return false;
		      }





          if( document.add_report_name_form.report_type_id.value=="-1")
		      {
		       showAlert("* Pls Select Report Type ...");
		  
		      document.add_report_name_form.report_type_id.focus();
		      document.getElementById('report_type_id').style.backgroundColor = "#FFEEEE";
		      return false;
		      }



               var x = document.add_report_name_form.rcrd_status.selectedIndex;
                var b=document.add_report_name_form.rcrd_status.options[x].value;
				
				if(b=="-1")
				{
					 showAlert("* Select Value For Activation Or Deactivation Option...");
				 document.add_report_name_form.rcrd_status.focus();
				 document.getElementById('rcrd_status').style.backgroundColor = "#FFEEEE";
				 return false;
				} 


				add_report_name_form.submit.disabled = true;
				add_report_name_form.submit.value = "Please wait...";
                  return true;   

}


 */

/*  function editdetails(report_id,report_name,report_xml_name, report_desc,report_type_id,Rcrdstatus)
   {
   
  			document.getElementById("report_id").value = report_id;
			document.getElementById("report_name").value = report_name;
			document.getElementById("report_xml_name").value = report_xml_name;
			document.getElementById("report_desc").value = report_desc;
		    document.getElementById("report_type_id").value = report_type_id;		  	  
		    document.getElementById("rcrd_status").value = Rcrdstatus;
		    document.getElementById("srno").value=report_id;
		    document.getElementById("uflag").value=parseInt(1);
            
           
  
   }
 */







$(document).ready (function(){
        $("#errordiv").hide();
        $("#update_msg_div").hide();
        
        });
        
function showAlert(value) {
           $("#errordiv").html(value);
            $("#errordiv").alert();
        //    alert(1+value);
            $("#errordiv").show().delay(1000).addClass("in").fadeOut(1500);
            //
        //     alert(2+value);
         /*   $("#errordiv").fadeTo(2000, 500).slideUp(500, function(){
            alert(2+value);
            $("#errordiv").alert('close');
            });   */
      //     alert(3+value);
        }


/* function saveData(){
        
        
     
        var formData= $('#add_report_name_form').serialize();
        
      
        $.getJSON('save_report_names', formData,function(data){
        
        document.getElementById('update_msg_div').style.display="inline";
        $('#update_msg_div').html(data.update_msg);
        
        add_report_name_form.submit.disabled = false;
        add_report_name_form.submit.value = "Submit";
     
     });
      
      
        
        }
 */


//save_country_names

</script>





 	   <style type="text/css">

span{}
.pagebanner{
display: none;
}
.pagelinks{
color: maroon;
margin-left: 0px;
margin-top:0px;
float:left;
}
#txt_filter{
display: none;
}
.exportlinks

.export{margin-left: 0px;}
.top-se{margin:0px 0px 0px 0px;}
.abc{ width:200px; margin:2px 0px 0px 0px; padding:0px 0px 0px 0px; margin:2px 0px 0px 20px\0/;}
.abc inr{ width:200px; margin:0px 0px 0px 0px; float:left; padding:0px 0px 0px 0px;}
 html>/**/body .abc, x:-moz-any-link {margin:0px 0px 0px -200px; float:left;   }
</style>
  </head>
  
  <body>
   
     
         <div class="row">
        <!-- left column -->
        <div class="col-md-12">
          <!-- general form elements -->
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Download Reports</h3>
              
              <div id="errordiv" class="alert alert-danger" style="color:#FF0000; margin:-18px 558px 8px 336px; padding:0px 0px 0px 17px; font-weight:bold;" ></div>
             <div id="update_msg_div" class="alert alert-success" style="padding:0px 0px 0px 17px; margin:-30px 558px 8px 336px;"></div>
     
            </div>
            <!-- /.box-header -->
            <!-- form start -->
			<form class="panel form-horizontal"  autocomplete="off" onsubmit="return(validateForm());" action="javascript:saveData();" name="add_report_name_form" id="add_report_name_form" method="post" >

        <div class="panel-body">








<div class="form-group">
<label for="inputEmail2" class="col-sm-3 control-label">Choose Report Type:</label>
<div class="col-sm-4">
<!-- <input name="srno" id="srno"  value="%{max_link_id}" readonly="readonly" onchange="chkNumeric(this);"> -->
<s:select cssClass="form-control" cssStyle="margin: 0px 0px 0px 0px;" list="lab_report_list" name="report_id" id="report_id" required="true"  headerKey="-1" headerValue="--Select Type--" ></s:select>
      
</div>
</div> <!-- / .form-group -->



<div class="form-group">
<label for="inputEmail2" class="col-sm-3 control-label">Status:</label>
<div class="col-sm-4">
<!-- <input name="srno" id="srno"  value="%{max_link_id}" readonly="readonly" onchange="chkNumeric(this);"> -->
<s:select cssClass="form-control" cssStyle="margin: 0px 0px 0px 0px;" list="#{'0':'Active','1':'Deactive'}" name="rcrd_status" id="rcrd_status" required="true"  headerKey="-1" headerValue="--Select Status--" ></s:select>
      
</div>
</div> <!-- / .form-group -->

 
  
<div class="form-group" style="margin: 0px 0px 0px 0px">
<div class="col-sm-offset-3 col-sm-4">
<button type="submit" class="btn btn-success btn-flat" disabled="disabled">Download</button>

</div>
</div> <!-- / .form-group -->

<s:token></s:token>
  <s:hidden id="uflag" name="uflag" value="0"></s:hidden>
  <s:hidden id="srno" name="srno"></s:hidden>
 

</div> <!-- / .radio -->
</form>
</div> <!-- / .col-sm-10 box box-primary -->
</div> <!-- / .form-group  col-md-12-->


</div><!-- / .form-group  row-->

 

   
   
  </body>
</html>
