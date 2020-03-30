const proxy = [
    {
      context: ['/api'],
      target: 'http://localhost:7001',
      pathRewrite: {'^/api' : ''}
    }
  ];
  module.exports = proxy;