<template>
<div id="app">
  <button type="button" name="button" @click.stop="invokeNative">调用原生功能</button>
</div>
</template>

<script>
export default {
  name: 'App',
  components: {},
  created() {
    console.log('APP created', this.$bridge)
  },
  mounted() {},
  methods: {
    invokeNative() {
      this.$bridge.call('eventName', {
        a: 1,
        b: 2
      }).then(res => {
        console.log('bridge res', res)
      }).catch(err => {
        console.log('bridge error', err)
      })
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
