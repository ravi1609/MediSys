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
function validateForm(){
       
       
           if( document.add_payment_mode_form.payment_mode_name.value=="")
		      { //alert("bank name cant be  blank");
		     // document.getElementById("errordiv").innerHTML="* Enter Value For Payment Mode Name....";
		      showAlert("* Enter Value For Report Name....");
		     // resetdatanotfounddiv();
		   //  alert("here");
		      document.add_payment_mode_form.report_name.focus();
		  //     alert("here");
		      document.getElementById('report_name').style.backgroundColor = "#FFEEEE";
		//       alert("here");
		      return false;
		      }
           if( document.add_report_name_form.report_xml_name.value=="")
		      { //alert("bank name cant be  blank");
		     // document.getElementById("errordiv").innerHTML="* Enter Value For Country Name....";
		      showAlert("* Enter Value For Report Xml Name....");
		     // resetdatanotfounddiv();
		   //  alert("here");
		      document.add_report_name_form.report_xml_name.focus();
		  //     alert("here");
		      document.getElementById('report_xml_name').style.backgroundColor = "#FFEEEE";
		//       alert("here");
		      return false;
		      }
      //alert("here");
             if( document.add_report_name_form.report_desc.value=="")
		      {
		      // alert("bank name cant be  blank");
		      //document.getElementById("errordiv").innerHTML="* Enter Value Country Description....";
		      showAlert("* Enter Value For Report Description....");
		     // resetdatanotfounddiv();
		      document.add_report_name_form.report_desc.focus();
		      document.getElementById('report_desc').style.backgroundColor = "#FFEEEE";
		      return false;
		      }
       //alert("here");
          if( document.add_report_name_form.report_type_id.value=="-1")
		      {
		      // alert("bank name cant be  blank");
		     // document.getElementById("errordiv").innerHTML="* Pls Select currency name...";
		      showAlert("* Pls Select Report Type ...");
		     // resetdatanotfounddiv();
		      document.add_report_name_form.report_type_id.focus();
		      document.getElementById('report_type_id').style.backgroundColor = "#FFEEEE";
		      return false;
		      }
               var x = document.add_payment_mode_form.rcrd_status.selectedIndex;
                var b=document.add_payment_mode_form.rcrd_status.options[x].value;
				
				if(b=="-1")
				{
			//	alert("Activation cant be  blank");
				// document.getElementById("errordiv").innerHTML="* Select Value For Activation Or Deactivation Option...";
				 showAlert("* Select Value For Activation Or Deactivation Option...");
				 document.add_report_name_form.rcrd_status.focus();
				 document.getElementById('rcrd_status').style.backgroundColor = "#FFEEEE";
				 return false;
				} 
        //alert("here");
          /*  var x = document.state_name_form.option_list.selectedIndex;
            var b=document.state_name_form.option_list.options[x].value;
				
				if(b=="-1")
				{
				
				 document.getElementById("errordiv").innerHTML="* Select Value For An Option ...";
				 document.state_name_form.option_list.focus();
				 document.getElementById('option_list').style.backgroundColor = "#FFEEEE";
				 return false;
				} */
    //alert("here");
				add_report_name_form.submit.disabled = true;
				add_report_name_form.submit.value = "Please wait...";
                  return true;   
}
 function editdetails(payment_mode_id,payment_mode_name,payment_mode_desc, display_order,Rcrdstatus)
   {
   
  			document.getElementById("payment_mode_id").value = payment_mode_id;
			  document.getElementById("payment_mode_name").value = payment_mode_name;
		  	document.getElementById("payment_mode_desc").value = payment_mode_desc;
			  document.getElementById("display_order").value = display_order; 	  
		    document.getElementById("rcrd_status").value = Rcrdstatus;
		    document.getElementById("srno").value=payment_mode_id;
		    document.getElementById("uflag").value=parseInt(1);
            
           
  
   }
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
function saveData(){
        
        
     /*    showAlertSuccess("data");
        return false;
      */   
        var formData= $('#add_payment_mode_form').serialize();
        
      //  alert(formData);
       // return false;
        $.getJSON('save_payment_mode', formData,function(data){
        
        document.getElementById('update_msg_div').style.display="inline";
        $('#update_msg_div').html(data.update_msg);
        
        add_payment_mode_form.submit.disabled = false;
        add_payment_mode_form.submit.value = "Submit";
      //  showAlertSuccess(data.update_msg);
     });
      
      
        //update_profile
        }
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
              <h3 class="box-title">Add Report Name</h3>
              
              <div id="errordiv" class="alert alert-danger" style="color:#FF0000; margin:-18px 558px 8px 336px; padding:0px 0px 0px 17px; font-weight:bold;" ></div>
             <div id="update_msg_div" class="alert alert-success" style="padding:0px 0px 0px 17px; margin:-30px 558px 8px 336px;"></div>
     
            </div>
            <!-- /.box-header -->
            <!-- form start -->
			<form class="panel form-horizontal"  autocomplete="off" onsubmit="return(validateForm());" action="javascript:saveData();" name="add_payment_mode_form" id="add_payment_mode_form" method="post" >

        <div class="panel-body">

<div class="form-group">
<label for="inputEmail2" class="col-sm-3 control-label">Report Id:</label>
<div class="col-sm-4">
 <s:textfield cssClass="form-control" name="payment_mode_id" id="payment_mode_id"   value="%{max_payment_mode_id}" readonly="true" onchange="chkNumeric(this);" ></s:textfield>
</div>
</div> <!-- / .form-group -->



<div class="form-group">
<label for="inputEmail2" class="col-sm-3 control-label">Payment Mode Name:</label>
<div class="col-sm-4">
 
 <s:textfield cssClass="form-control" name="payment_mode_name" id="payment_mode_name"   onkeyup="chkStringspc(this);"></s:textfield>
</div>
</div> <!-- / .form-group -->


<div class="form-group">
<label for="inputEmail2" class="col-sm-3 control-label">Payment Mode Description </label>
<div class="col-sm-4">
<s:textfield cssClass="form-control" name="payment_mode_desc" id="payment_mode_desc"  required="true" onkeyup="chkRemark(this);" ></s:textfield>
</div>
</div> <!-- / .form-group -->


<div class="form-group">
<label for="inputEmail2" class="col-sm-3 control-label">Display Order </label>
<div class="col-sm-4">
<s:textfield cssClass="form-control" name="display_order" id="display_order"  required="true" onkeyup="chkNumeric(this);" ></s:textfield>
</div>
</div> <!-- / .form-group -->


<div class="form-group">
<label for="inputEmail2" class="col-sm-3 control-label">Status:</label>
<div class="col-sm-4">
<!-- <input name="srno" id="srno"  value="%{max_link_id}" readonly="readonly" onchange="chkNumeric(this);"> -->
<s:select cssClass="form-control" cssStyle="margin: 0px 0px 0px 0px;" list="#{'0':'Active','1':'Deactive'}" name="rcrd_status" id="rcrd_status" required="true"  headerKey="-1" headerValue="--Select Status--" ></s:select>
      
</div>
</div> <!-- / .form-group -->

 
  
<div class="form-group" style="margin: 0px 0px 0px 457px">
<div class="col-sm-offset-2 col-sm-10">
<button type="submit" class="btn btn-success btn-flat">Submit</button>

<button type="reset" class="btn btn-warning btn-flat">Reset</button>
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

 

            <s:if test="payment_mode_list!=null">
  	<display:table  export="true" id="payment_master_table" requestURIcontext="true"  name="payment_mode_list" requestURI="add_payment_mode" pagesize="30" cellpadding="2px;" cellspacing="0px;" style="margin:10px 0px 10px 0px; padding:0px 0px 0px 0px; width:723px; border:#98AFC7 1px solid; color:#115684; font-size:10px;"  >
				<display:setProperty name="basic.show.header" value="true"></display:setProperty>				
				<display:setProperty name="basic.msg.empty_list" value=" "></display:setProperty>
				<display:setProperty name="paging.banner.onepage" value=" Page One"></display:setProperty>				
				<display:setProperty name="basic.empty.showtable" value="false"></display:setProperty>
				<display:setProperty name="basic.msg.empty_list" value=" "></display:setProperty>
				<display:setProperty name="export.pdf" value="true"></display:setProperty>
				<display:setProperty name="export.csv" value="true"></display:setProperty>
				<display:setProperty name="export.xml" value="true"></display:setProperty>
				<display:setProperty name="export.pdf.filename" value="ReportDetails.pdf"/>
        <display:setProperty name="export.excel.filename" value="ReportDetails.xlsx"/>
				<display:setProperty name="export.csv.filename" value="ReportDetails.csv"/>
				<display:column property="payment_mode_id" title="Payment Mode Id" style="width: 80px; text-align:center;" ></display:column>
				<display:column property="payment_mode_name" title="Payment Mode Name" style="width: 80px; text-align:center;" ></display:column>
		
				<display:column property="payment_mode_desc" title="Mode Description" style="width: 80px; text-align:center;"></display:column>						
				<display:column property="display_order" title="Display Order" style="width: 80px; text-align:center;"></display:column>
				<display:column property="status" title="Status" style="width: 40px; text-align:center;"></display:column>
				<display:column property="created_date" title="Created Date" style="width: 100px; text-align:center;"></display:column>
				<display:column property="created_by" title="Created By" style="width: 70px; text-align:center;"></display:column>
				<display:column title="Edit" media="html" style="width: 40px; text-align:center;">
              <input type="button" onclick="editdetails('${payment_master_table.payment_mode_id}','${payment_master_table.payment_mode_name}', '${payment_master_table.payment_mode_desc}','${payment_master_table.display_order}','${payment_master_table.rcrd_status}');" value="EDIT">
			 </display:column>
 			</display:table>
  
  </s:if>
   
   
   
  </body>
</html>



