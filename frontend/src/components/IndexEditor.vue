<template>
    <div>
        <h4 class="title is-4">
            Rediger forsiden
        </h4>
        <article class="message">
            <div class="message-header">
                HTML editor
            </div>

            <div class="message-body">
                <html-editor :content.sync="page.content"/>
            </div>
        </article>

        <article class="message">
            <div class="message-header">
                Forhåndsvisning
            </div>

            <div class="message-body">
                <div class="content"
                     v-html="page.content">
                </div>
            </div>
        </article>
    </div>
</template>

<script>
import HtmlEditor from './HtmlEditor'

export default {
    components: {HtmlEditor},
    name: 'index-editor',
    data () {
        return {
            page: {
                content: null
            }
        }
    }
}
</script>
