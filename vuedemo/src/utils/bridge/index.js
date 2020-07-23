/**
 * H5与原生交互可用方法如下:
 * bridge.call('eventName', params).then(res => {}).catch(err) {}
 * 或者
 * this.$bridge.call('eventName', params).then(res => {}).catch(err) {}
 */

import emitter from './events'
import browser from '@/utils/browser'

class Bridge {
  constructor() {
    this.browser = {
      platform: '',
      isQQ: false,
      isWeiXin: false
    }
    this.getBrowserInfo()
  }

  getBrowserInfo() {
    const {
      versions: {
        ios,
        isQQ,
        isWeiXin
      }
    } = browser()
    const platform = ios ? 'ios' : 'android'
    this.browser = {
      platform,
      isQQ,
      isWeiXin
    }
  }

  registerEvent(eventName, params) {
    console.log('浏览器信息', this.browser)
    console.log('事件名称', eventName)
    console.log('事件参数', params)
    return new Promise((resolve, reject) => {
      try {
        if (this.browser.platform === 'ios') {
          window.webkit.messageHandlers.bridgeDemoNative.postMessage({
            funcName: eventName,
            params
          })
        } else {
          // 此处必须直接调用，否则安卓会报错
          window.bridgeDemoNative[eventName](JSON.stringify(params))
        }
        resolve()
      } catch (err) {
        console.error(`调用原生方法 ${eventName} 失败`, err)
        reject(err)
      }
    })
  }

  async call(eventName, params = {}) {
    return new Promise(async (resolve, reject) => {
      try {
        await this.registerEvent(eventName, params)
        emitter.removeAllListeners(eventName)
        emitter.on(eventName, function(res) {
          resolve(res)
        })
      } catch (err) {
        reject(err)
      }
    })
  }
}

export default new Bridge()
