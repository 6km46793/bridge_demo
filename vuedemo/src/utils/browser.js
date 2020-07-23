// 获取浏览器相关信息
export default function getBrowserInfo() {
  const u = navigator.userAgent
  const browser = {
    versions: {
      trident: u.indexOf('Trident') > -1, // IE内核
      presto: u.indexOf('Presto') > -1, // opera内核
      webKit: u.indexOf('AppleWebKit') > -1, // 苹果、谷歌内核
      gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') === -1, // 火狐内核
      mobile: !!u.match(/AppleWebKit.*Mobile.*/), // 是否为移动终端
      ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), // ios终端
      android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, // android终端或uc浏览器
      iPhone: u.indexOf('iPhone') > -1, // 是否为iPhone或者QQHD浏览器
      iPad: u.indexOf('iPad') > -1, // 是否iPad
      safari: u.indexOf('Safari') > -1, // 是否Safari
      webApp: u.indexOf('Safari') === -1 // 是否web应用程序，没有头部与底部
    },
    language: (navigator.browserLanguage || navigator.language).toLowerCase()
  }
  if (browser.versions.mobile) {
    // 判断是否是移动设备打开
    browser.versions.isWeiXin = /MicroMessenger/i.test(u)
    browser.versions.isWeibo = /WeiBo/i.test(u)
    browser.versions.isQQ = /QQ/i.test(u)
  }
  return browser
}
