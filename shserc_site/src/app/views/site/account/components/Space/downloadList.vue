<template>
  <div class="ranking">
    <List :source="[1, 2, 3, 4, 5]"> 
        <template slot="item">
               <div class="ranking_square">
						
						<div class="details downwide">
							<a href="/Resource/View/47" title="随班就读智力障碍学生同伴交往的案例研究"><span class="restitle">随班就读智力障碍学生同伴交往的案例研究</span></a><br>

							<div class="info">
								作者：<span>曾凡林、陈奇                                                                                              </span>
								下载日期：<span>2020/12/21</span>
								格式：<span>.DOC</span>
							</div>
						</div>
	<a href="/My/DeleteDownload/52679" class="btn btn-default">删除下载</a>
	</div>        
        </template>
    </List>
  </div>
</template>

<script>
import List from "@/components/List/index";

export default {
  components: { List },
};
</script>