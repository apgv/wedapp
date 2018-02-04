<template>
    <div class="has-text-centered">
        <div class="fa-3x">
            <i class="fas fa-spinner fa-spin"></i>
        </div>
        <div>Logger inn...</div>
    </div>
</template>

<script>
export default {
    name: 'auth0-callback',
    props: ['auth'],
    data () {
        this.auth.handleAuthentication()
        return {}
    }
}
</script>
