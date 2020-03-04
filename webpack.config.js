const path = require('path');

module.exports = {
  entry: './src/main/js/app.js',
  devtool: 'sourcemaps',
  cache: true,
  mode: 'development',
  output: {
    path: path.resolve(__dirname, './src/main/webapp/static'),
    filename: 'app-bundle.js'
  },
  module: {
    rules: [
      {
        test: path.join(__dirname, '.'),
        exclude: /(node_modules)/,
        use: [{
          loader: 'babel-loader',
          options: {
            presets: ["@babel/preset-env", "@babel/preset-react"]
          }
        }]
      },
      {
        test: /\.s?css$/,
        use: [
          "style-loader" ,
          "css-loader"
        ]
      },
    ]
  }
};
