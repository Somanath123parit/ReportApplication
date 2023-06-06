<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>Reports Application</title>
</head>
<body>
   <div class="container">
      <h2 class="pb-3 pt-3">Reports Application</h2>
        <f:form action="search" modelAttribute="search" method="post">
               <table>
                     <tr>
                         <td>Plan Name: </td>
                         <td>
                              <f:select path="planName">
                                     <f:option  value="">-Select-</f:option>
                                      <f:options  items="${names}"/>
                              </f:select>
                         </td>
                         <td>Plan Status: </td>
                         <td>
                              <f:select path="planStatus">
                                     <f:option  value="">-Select-</f:option>
                                     <f:options  items="${status}"/>
                              </f:select>
                         </td>
                          <td>Gender: </td>
                         <td>
                              <f:select path="gender">
                                     <f:option  value="">-Select-</f:option>
                                     <f:option  value="male">Male</f:option>
                                     <f:option  value="Fe-Male">Fe-Male</f:option>
                              </f:select>
                         </td>
                     </tr>
                     <tr>
                          <td>Start Date:</td>
                          <td>  <f:input  type="date" date-date-format="yyyy-mm-dd"  path="startDate"></f:input></td>
                          <td>End Date:</td>
                          <td><f:input  type="date"  path="endDate"></f:input></td>
                          
                     </tr>
                     <tr>
                        <td><a href="/" class="btn btn-secondary">Reset</a></td>
                       <td>
                          <input type="submit" value="search" class="btn btn-primary"/>
                       </td>
                     </tr>
                     
               </table>
        </f:form>
              
              <hr/>
                 <table class="table table-striped  table-hover">
                      <thead>
                            <tr>
                                 <th>Sr.no</th>
                                 <th>Holder Name</th>
                                 <th>Plan Name</th>
                                 <th>Plan Status</th>
                                 <th>Start Date</th>
                                 <th>End Date</th>
                                 <th>Benefit Amt</th>
                            </tr>
                      </thead>
                      <tbody>
                              <c:forEach items="${plans}"  var="plan"  varStatus="index">
                                  <tr>
                                      <td>${index.count}</td>
                                      <td>${plan.citizenName}</td>
                                      <td>${plan.gender}</td>
                                       <td>${plan.planName}</td>
                                      <td>${plan.planStatus}</td>
                                      <td>${plan.planStartDate}</td>
                                      <td>${plan.planEndDate}</td>
                                        <td>${plan.benefitAmt}</td>  
                                   </tr>   
                              </c:forEach>
                                  <tr>
                                     <c:if test="${empty plans}">
                                           <td colspan="8" style="text-align:center">No records found</td>
                                     </c:if>
                                 </tr> 
                      </tbody>
                 </table>
              
              
                 Export    :           <a href="excel">Excel</a>             <a href="pdf">Pdf</a>
   </div>
   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
   <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>