<#list list as menu>
insert into menu(id,text,parentID,url,orderNo) values(${menu.id},'${menu.text}',${menu.parentID},'${menu.url}',${menu.orderNo});
</#list>