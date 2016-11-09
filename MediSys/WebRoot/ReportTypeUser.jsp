<@taglib uri="/struts-tags" prefix="s"/>
<html>
<title>this is the jsp for adding the user type report </title>
<head>

<script type="text/javascript" src="validate.js">
 
</script>
  
  
  
  
  
  
  


</head>
<body class="container">


  <form name="add_user_type_report_form" id="add_user_type_report_form" method="post" onsubmit="validateAll();" action="#">
  
    <div class="form-container">
    <div class="col-md-4">
    <s:textfield  name="user_type_id" id="user_type_id" disabled="true" value="%{max_report_type_user_id}"/>
      </div>   
      
       <div class="col-md-4">
    <s:textfield  name="user_type_name" id="user_type_name" disabled="false"/>
      </div> 
      
       <div class="col-md-4">
    <s:textfield  name="user_type_desc" id="user_type_desc" disabled="false"/>
      </div> 
      
      
       <div class="col-md-4">
    <s:textfield  name="user_type_display" id="user_type_display" disabled="false"/>
      </div>
      
      
       <div class="col-md-4">
    <s:select  name="rcrd_status" id="rcrd_status" list="#{'-1':'--Select--','0':'Active','1':'Deactive'}" />
      </div> 
      
      
      
      <s:submit value="Add User"/>
      <s:reset value="Reset"/>
     
    </div>
  </form>
  
  
  



</body>


</html>
