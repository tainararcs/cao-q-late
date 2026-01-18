<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Mensagem de erro (request ou sessão) -->
<c:if test="${not empty requestScope.error}">
   <div class="msg-error feedback">${requestScope.error}</div>
</c:if>
<c:if test="${not empty sessionScope.error}">
   <div class="msg-error feedback">${sessionScope.error}</div>
   <c:remove var="error" scope="session" />
</c:if>
<!-- Mensagem de sucesso (request ou sessão) -->
<c:if test="${not empty requestScope.msg}">
   <div class="msg-success feedback">${requestScope.msg}</div>
</c:if>
<c:if test="${not empty sessionScope.msg}">
   <div class="msg-success feedback">${sessionScope.msg}</div>
   <c:remove var="msg" scope="session" />
</c:if>
<script>
   const feedback = document.querySelector('.feedback');
   if (feedback) {
       setTimeout(() => feedback.classList.add('show'), 100);
       setTimeout(() => {
           feedback.classList.remove('show');
           setTimeout(() => feedback.remove(), 500);
       }, 3000);
   }
</script>
