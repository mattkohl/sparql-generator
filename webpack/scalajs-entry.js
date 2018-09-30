if (process.env.NODE_ENV === "production") {
    const opt = require("./sparql-generator-opt.js");
    opt.entrypoint.main();
    module.exports = opt;
} else {
    var exports = window;
    exports.require = require("./sparql-generator-fastopt-entrypoint.js").require;
    window.global = window;

    const fastOpt = require("./sparql-generator-fastopt.js");
    fastOpt.entrypoint.main()
    module.exports = fastOpt;

    if (module.hot) {
        module.hot.accept();
    }
}
