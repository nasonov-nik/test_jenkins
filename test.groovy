dors = "df,dfdf,dfdf,dfdf,dfd,fdf"
dors = dors.split(',')
println(dors.toList())
jobs = [:]
jobs.put("first", "did")
jobs.put("second", "dod")

runtime_props = [:]

runtime_props.put("conf", [(jobs."first"): jobs."second"])

println(runtime_props)

//runtime_props.conf = [:]
//runtime_props.conf.put("lol", null)
//runtime_props.conf.put("lol2", "lolo")
//if (!runtime_props.conf.lol) {
//    runtime_props.conf.lol = "pampam"
//}
//println(runtime_props.conf.lol)
