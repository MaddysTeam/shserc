<template>
  <div class="m_30_bottom">
    <el-row :gutter="10">
      <el-col :span="15">
        <!--   resource info start -->
        <div class="block_panel">
          <p class="panel_title">
            <span> 首页 > 浏览资源</span>
          </p>

          <div class="body">
            <div class="body res_details">
              <div class="title filetype">
                <div class="mp4"></div>
                “《好脏的哈利——九宫格翻翻书》绘本延伸美术活动材料包”的运用
              </div>

              <div>
                <div>
                  <embed
                    type="application/x-mplayer2"
                    pluginspage="http://www.microsoft.com/Windows/MediaPlayer/"
                    width="100%"
                    height="520"
                    src="http://tjcdn.shec.edu.cn/20171113/fb608b8f-9781-4e09-a95c-e08539a7ee82/“《好脏的哈利——九宫格翻翻书》绘本延伸美术活动材料包”的运用（1080P）.mp4"
                    wmode="transparent"
                    autostart="false"
                  />
                </div>
              </div>

              <div class="opbar">
                <div class="eval_star">
                  <b>点击评分：</b>
                  <el-rate v-model="value2" :colors="colors"> </el-rate>
                </div>
                <div class="buttons">
                  <el-button size="larger" type="danger" id="favorite">
                    <i class="fa fa-download"></i> 收 藏
                  </el-button>
                  <el-button size="larger" type="primary" id="favorite">
                    <i class="fa fa-download"></i> 复制地址
                  </el-button>

                  <!-- <a
                    href="http://tjcdn.shec.edu.cn/20171113/fb608b8f-9781-4e09-a95c-e08539a7ee82/“《好脏的哈利——九宫格翻翻书》绘本延伸美术活动材料包”的运用（1080P）.mp4"
                    id="localPlay"
                    title="视频不能内嵌播放时，请选择本地播放模式"
                  >
                    <button class="btn btn-lg btn-info btn-op btn-default">
                      <i class="fa fa-play"></i>
                      本地播放
                    </button>
                  </a>
                  <button
                    id="toClipboard"
                    class="btn btn-lg btn-info btn-op btn-default zeroclipboard-is-hover"
                    title="点击复制资源地址到剪贴板中"
                    data-clipboard-text="http://tjcdn.shec.edu.cn/20171113/fb608b8f-9781-4e09-a95c-e08539a7ee82/“《好脏的哈利——九宫格翻翻书》绘本延伸美术活动材料包”的运用（1080P）.mp4"
                  >
                    <i class="fa fa-copy"></i>
                    复制地址
                  </button> -->
                </div>
              </div>

              <div class="info">
                <div class="dzstyle">
                  <div class="boxes">
                    <div class="half left">
                      <div>
                        <!-- <p><strong class="score">5</strong></p>
                                    -->
                        <p>共有 2 人参与了评价</p>
                      </div>
                    </div>
                    <div class="half right">
                      <!-- <ul class="bars">
									<li>5分 <div class="bar"><div class="val" style="width:100%}"></div></div> 2 人</li>
									<li>4分 <div class="bar"><div class="val" style="width:0%}"></div></div> 0 人</li>
									<li>3分 <div class="bar"><div class="val" style="width:0%}"></div></div> 0 人</li>
									<li>2分 <div class="bar"><div class="val" style="width:0%}"></div></div> 0 人</li>
									<li>1分 <div class="bar"><div class="val" style="width:0%}"></div></div> 0 人</li>
								</ul> -->
                      <el-progress
                        :text-inside="true"
                        :stroke-width="26"
                        :percentage="70"
                        class="m_10_bottom"
                      ></el-progress>
                      <p></p>
                      <el-progress
                        :text-inside="true"
                        :stroke-width="24"
                        :percentage="100"
                        class="m_10_bottom"
                        status="success"
                      ></el-progress>
                      <el-progress
                        :text-inside="true"
                        :stroke-width="22"
                        :percentage="80"
                        class="m_10_bottom"
                        status="warning"
                      ></el-progress>
                      <el-progress
                        :text-inside="true"
                        :stroke-width="20"
                        :percentage="50"
                        class="m_10_bottom"
                        status="exception"
                      ></el-progress>
                    </div>
                  </div>
                  <div style="clear: both"></div>
                </div>

                <div class="board first">
                  <p><span>关 键 字： 绘本、延伸活动、美术 </span></p>
                  <p>
                    <span>资源类型： 教学实录</span><span>媒体格式： 视频</span>
                  </p>
                  <p>
                    <span>上传日期： 2017/11/13</span
                    ><span>浏览次数： 6426 次</span>
                  </p>

                  <p><span>收藏次数： 7</span><span>下载次数： 1 次</span></p>

                  <p></p>
                </div>
                <div class="board">
                  <p>
                    绘本延伸美术活动，是以绘本为载体，选取绘本故事中的情节、画面等元素进行设计的美术活动。根据绘本《好脏的哈利》所设计的《好脏的哈利——九宫格翻翻书》，由9幅图画组成，每一幅图画代表绘本故事中的一个情节，学生需要在阅读绘本后，根据书中内容完成9幅图画的绘制与句子填写，并进行剪裁、翻折与封面设计，最后完成属于自己的小绘本。在此过程中帮助学生发展语言表达能力，提升双手动作的精确性。
                    本视频包含：1、美术活动材料包内容介绍；2、材料包的使用方法演示；3、材料包使用注意事项。
                  </p>
                  <h3>详细属性</h3>
                </div>
                <div class="board">
                  <p>
                    <span>残疾类别： 智力残疾</span
                    ><span>学　　段： 义务教育</span>
                  </p>
                  <p>
                    <span>领　　域： 课程与教学</span
                    ><span>年　　级： 小学段</span>
                  </p>
                  <p>
                    <span>安置形式： 随班就读</span
                    ><span>学校类别： 普通学校</span>
                  </p>
                  <p>
                    <span>学　　科： 艺术</span><span>作　　者： 仇佩琦</span>
                  </p>
                  <p>
                    <span>单　　位： 虹口区特殊教育指导中心</span
                    ><span>地　　址： 虹口区天宝西路242弄40号</span>
                  </p>
                </div>
              </div>

              <div class="comment">
                <div class="comment_input">
                  <h3>您的评论</h3>
                  <textarea></textarea>
                  <div class="comment_num">240</div>
                  <el-button type="info" disabled="disabled">
                    登录后可发表评论
                  </el-button>
                </div>

                <div class="comment_count">
                 <el-tag type="danger" class="font14"><i class="el-icon-info"></i> 共 <strong>3</strong> 条评论</el-tag>
                </div>
                <div class="comment_list">
                  <list :source="[1, 2, 3,]">
                    <template>
                      <img
                          class="cover"
                          src="http://tjcdn.shec.edu.cn/demo/7.jpg"
                          style="height: 100px"
                      />
                      <div class="details">
                        <p class="font14"><el-tag>杨美玲</el-tag> 发表于： <el-tag type="success">2019/9/12</el-tag></p>
                       <br />
                        <p class="snippet">
                          随着特殊教育的“零拒绝”的发展，越来越多的中、重度甚至极重度智障儿童进入学校。由于智障学生认知水平和语言能力的缺陷，他们
                          ...
                        </p>
                      </div>
                    </template>
                  </list>
                </div>
                <!-- <div class="comment_more">
                  <button
                    id="getmore"
                    class="btn btn-op btn-default"
                    disabled=""
                  >
                    加载更多评论
                  </button>
                </div> -->
              </div>
            </div>
          </div>
        </div>
        <!-- resource  info  end -->
      </el-col>

      <el-col :span="8" :offset="1">
        <!--  relative  resource list start -->
        <div class="block_panel">
          <p class="panel_title">
            <span><i class="el-icon-user-solid"></i> 相关资源</span>
          </p>

          <div class="body">
            <ul class="compact_list">
              <li>
                <a href="/Resource/View/1333"
                  ><span class="square">1</span> 解决问题的策略——转化</a
                >
              </li>
              <li>
                <a href="/Resource/View/1334"
                  ><span class="square">2</span>
                  认知数学《认识整点》（课堂实录）</a
                >
              </li>
              <li>
                <a href="/Resource/View/1335"
                  ><span class="square">3</span> 我的学校</a
                >
              </li>
              <li>
                <a href="/Resource/View/1332"><span>4</span> 荷叶圆圆</a>
              </li>
              <li>
                <a href="/Resource/View/1553"
                  ><span>5</span> 品德与社会《绿色的呼唤》</a
                >
              </li>
              <li>
                <a href="/Resource/View/1336"><span>6</span> 有序数对</a>
              </li>
              <li>
                <a href="/Resource/View/5480"
                  ><span>7</span>
                  “《好脏的哈利——九宫格翻翻书》绘本延伸美术活动材料包”的运用</a
                >
              </li>
              <li>
                <a href="/Resource/View/1330"
                  ><span>8</span> 表情表情我知道（教学实录）</a
                >
              </li>
              <li>
                <a href="/Resource/View/283"
                  ><span>9</span> 实用数学《时分的认识》</a
                >
              </li>
              <li>
                <a href="/Resource/View/5476"
                  ><span>10</span>
                  “《我妈妈——拎包式立体贺卡》绘本延伸美术活动材料包”的运用</a
                >
              </li>
              <li>
                <a href="/Resource/View/276"
                  ><span>11</span> 《荷花》——聋校语文教学实录</a
                >
              </li>
              <li>
                <a href="/Resource/View/1382"><span>12</span> 面积的估测</a>
              </li>
              <li>
                <a href="/Resource/View/1347"
                  ><span>13</span> 六面体的平行透视</a
                >
              </li>
              <li>
                <a href="/Resource/View/1378"><span>14</span> 掷数点块</a>
              </li>
              <li>
                <a href="/Resource/View/1337"><span>15</span> 圆的面积</a>
              </li>
            </ul>
          </div>
        </div>
        <!--  relative  resource list end -->

        <!--  relative  resource list start -->
        <div class="block_panel">
          <p class="panel_title">
            <span><i class="el-icon-user-solid"></i> 热门资源</span>
          </p>
          <div>右侧列表</div>
        </div>
        <!--  relative  resource list end -->
      </el-col>
    </el-row>
  </div>
</template>
<script>
import list from "@/components/List";

export default {
  components: { list },

  data() {
    return {
      value1: null,
      value2: null,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"], // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
    };
  },
};
</script>

<style scoped>
</style>