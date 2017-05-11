<li class="treeview">
	<a href="javascript:void(0);" onclick="forwardMenu('${menu.url!}');">
		<i class="fa ${menu.icon!}"></i> <span>${menu.text!}</span>
		<span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
        </span>
	</a>
	<ul class="treeview-menu">
		${subMenus}
	</ul>
</li>