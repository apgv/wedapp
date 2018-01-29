<!--https://codelike.pro/create-a-contenteditable-with-vue/-->
<template>
    <div contenteditable="true"
         @input="$emit('update:content', $event.target.innerText)">
    </div>
</template>

<script>
export default {
    name: 'html-editor',
    props: ['content'],
    mounted: function () {
        this.$el.innerText = this.content
    }
}
</script>
