const EventEmitter = require('events')

class SelfEmitter extends EventEmitter {}
const selfEmitter = new SelfEmitter()
window.bridgeDemoEmitter = selfEmitter

export default selfEmitter
