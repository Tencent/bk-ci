const { src, dest, parallel, series, task } = require('gulp')
const svgSprite = require('gulp-svg-sprite')
const rename = require('gulp-rename')
const Ora = require('ora')
const yargs = require('yargs')
const argv = yargs.alias({
    'dist': 'd',
    'env': 'e',
    'lsVersion': 'l',
    'scope': 's',
    'public': 'p'
}).default({
    'dist': 'frontend',
    'env': 'master',
    'lsVersion': 'dev',
    'public': false
}).describe({
    'dist': 'build output dist directory',
    'env': 'environment [dev, test, master, external]',
    'lsVersion': 'localStorage version'
}).argv
const { dist, env, lsVersion, scope, public } = argv

const svgSpriteConfig = {
    mode: {
        symbol: true
    }
};

function taskGenerator(type) {
    return () => {
        return src(`./svg-sprites/${type}/*.svg`)
        .pipe(svgSprite(svgSpriteConfig))
        .pipe(dest(`./svg-sprites/dist/${type}`))
    }
}

function renameSvg (type) {
    return () => {
        return src(`./svg-sprites/dist/${type}/symbol/svg/sprite.symbol.svg`)
            .pipe(rename(`${type}_sprite.svg`))
            .pipe(dest(`${dist}/svg-sprites/`))
    }
}

let scopeCli = ''
if (scope && typeof scope === 'string') {
    scopeCli = scope
        .split(',')
        .filter(v => v.trim())
        .map(name => `--scope devops-${name} `)
        .join()
}

task('devops', series([taskGenerator('devops'), renameSvg('devops')]))
task('pipeline', series([taskGenerator('pipeline'), renameSvg('pipeline')]))
task('copy', () => src(['common-lib/**'], { 'base': '.' }).pipe(dest(`${dist}/`)))
task('build', cb => {
    const spinner = new Ora('building bk-ci frontend project').start()
    require('child_process').exec(`lerna run public:${env} ${scopeCli}--parallel -- --env.dist=${dist} --env.lsVersion=${lsVersion}`, {
        maxBuffer: 5000 * 1024
    }, (err, res) => {
        if (err) {
            console.log(err)
            process.exit(1)
        }
        spinner.succeed(`Finished building bk-ci frontend project`)
        cb()
    })
})

exports.default = parallel('build', ...(public ? ['devops', 'pipeline', 'copy'] : []))
