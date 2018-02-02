<template>
    <article v-if="show"
             class="message is-link">
        <div class="message-header">
            <p>Info</p>
        </div>
        <div class="message-body">
            {{message}}
        </div>
    </article>
</template>

<script>
export default {
    name: 'info-message',
    props: ['show', 'message']
}
</script>
