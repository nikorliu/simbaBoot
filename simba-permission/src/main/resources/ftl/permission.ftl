<#list allOrgList as org>
insert into org(id,text,parentID,orderNo) values(${org.id},'${org.text!}',${org.parentID},${org.orderNo!});
</#list>
<#list allRoleList as role>
insert into role(name,description) values('${role.name}','${role.description!}');
</#list>
<#list userOrgList as userOrg>
insert into userOrg(id,userAccount,orgID) values(${userOrg.id},'${userOrg.userAccount}',${userOrg.orgID});
</#list>
<#list userList as user>
insert into systemUser(account,name,pwd) values('${user.account}','${user.name}','${user.pwd}');
</#list>
<#list permissionList as permission>
insert into permission(id,text,url,parentID) values(${permission.id},'${permission.text!}','${permission.url!}',${permission.parentID});
</#list>
<#list rolePermissionList as rolePermission>
insert into rolePermission(id,roleName,permissionID) values(${rolePermission.id},'${rolePermission.roleName}',${rolePermission.permissionID});
</#list>
<#list userRoleList as userRole>
insert into userRole(userAccount,roleName) values('${userRole.userAccount}','${userRole.roleName}');
</#list>
<#list orgRoleList as orgRole>
insert into orgRole(id,orgID,roleName) values(${orgRole.id},${orgRole.orgID},'${orgRole.roleName}');
</#list>
<#list orgExtSQLs as orgExtSql>
${orgExtSql}
</#list>
<#list userExtSQLs as userExtSql>
${userExtSql}
</#list>