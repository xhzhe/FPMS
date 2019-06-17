<template>
  <div class="components-container">
    <div>
          <div style="background:#eee;padding: 20px">
        <Card :bordered="false">
            <p slot="title">商品信息</p>
            <div class="demo-split">
            <Split v-model="split2" mode="vertical">
            <div slot="top" class="demo-split-pane">
                信息一
            </div>
            <div slot="bottom" class="demo-split-pane">
                信息二
            </div>
           </Split>
           </div>
        </Card>
    </div>
      <tinymce v-model="content" :height="100" />
      <tinymce v-model="content" :height="100" />
    </div>
    <Button type="success">保存</Button>
    <Button type="error" >取消</Button>
  </div>
</template>

<script>
import Tinymce from '@/components/Tinymce'

export default {
  name: 'TinymceDemo',
  components: { Tinymce },
  data() {
    return {
      content: ' ',
      split2: 0.5
    }
  }
}
</script>

<style scoped>
.editor-content{
  margin-top: 20px;
}
 .demo-split{
        height: 200px;
        border: 1px solid #dcdee2;
    }
    .demo-split-pane{
        padding: 10px;
    }
</style>

