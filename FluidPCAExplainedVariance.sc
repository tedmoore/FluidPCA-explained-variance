FluidPCAExplainedVariance {

	*new {
		arg fluid_pca_json_path;
		var file = File.readAllString(fluid_pca_json_path);
		var start = file.findRegexp("\"values\": \\[");
		var end, substr, arr, sum = 0, vals, weights;
		start = file.findRegexp("\\[",start[0][0]);
		end = file.findRegexp("\\]",start[0][0]);
		substr = file[start[0][0]..end[0][0]];
		arr = substr.interpret;
		vals = arr.collect{
			arg val;
			var sqr = pow(val.asFloat,2);
			sum = sum + sqr;
			sqr;
		};
		weights = vals.collect{
			arg val;
			val / sum;
		};
		^weights;
	}
}