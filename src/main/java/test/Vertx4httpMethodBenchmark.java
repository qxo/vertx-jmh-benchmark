package test;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@Fork(1)
@Threads(3)
@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class Vertx4httpMethodBenchmark {

	private static final io.netty.handler.codec.http.HttpMethod METHOD = io.netty.handler.codec.http.HttpMethod.DELETE;
	
	@Benchmark
	//@OperationsPerInvocation(3)
	public Object oldWay4PUT() {//final Blackhole hole
		 io.netty.handler.codec.http.HttpMethod  input =  io.netty.handler.codec.http.HttpMethod.PUT;
		 io.vertx.core.http.HttpMethod ret = toVertHttpMethod(input);
		return ret;
	}
	
	@Benchmark
	//@OperationsPerInvocation(3)
	public Object newWay4PUT() {//final Blackhole hole
		 io.netty.handler.codec.http.HttpMethod  input =  io.netty.handler.codec.http.HttpMethod.PUT;
		 io.vertx.core.http.HttpMethod ret = toVertHttpMethodNew(input);
		return ret;
	}
	
	@Benchmark
	//@OperationsPerInvocation(3)
	public Object oldWay4GET() {//final Blackhole hole
		 io.netty.handler.codec.http.HttpMethod  input =  io.netty.handler.codec.http.HttpMethod.GET;
		 io.vertx.core.http.HttpMethod ret = toVertHttpMethod(input);
		return ret;
	}
	
	@Benchmark
	//@OperationsPerInvocation(3)
	public Object newWay4GET() {//final Blackhole hole
		 io.netty.handler.codec.http.HttpMethod  input =  io.netty.handler.codec.http.HttpMethod.GET;
		 io.vertx.core.http.HttpMethod ret = toVertHttpMethodNew(input);
		return ret;
	}

	@Benchmark
	//@OperationsPerInvocation(3)
	public Object oldWay4HEAD() {//final Blackhole hole
		 io.netty.handler.codec.http.HttpMethod  input =  io.netty.handler.codec.http.HttpMethod.HEAD;
		 io.vertx.core.http.HttpMethod ret = toVertHttpMethod(input);
		return ret;
	}
	
	@Benchmark
	//@OperationsPerInvocation(3)
	public Object newWay4HEAD() {//final Blackhole hole
		 io.netty.handler.codec.http.HttpMethod  input =  io.netty.handler.codec.http.HttpMethod.HEAD;
		 io.vertx.core.http.HttpMethod ret = toVertHttpMethodNew(input);
		return ret;
	}
	
	  public static io.vertx.core.http.HttpMethod toVertHttpMethod(io.netty.handler.codec.http.HttpMethod nettyHttpMethod) {
		  io.vertx.core.http.HttpMethod method;
	      final String sMethod = nettyHttpMethod.toString();
	      try {
	        method = io.vertx.core.http.HttpMethod.valueOf(sMethod);
	      } catch (IllegalArgumentException e) {
	        method = io.vertx.core.http.HttpMethod.OTHER;
	      }
	    return method;
	  }
	  
	  
	  public static io.vertx.core.http.HttpMethod toVertHttpMethodNew(io.netty.handler.codec.http.HttpMethod nettyHttpMethod) {
		 if (io.netty.handler.codec.http.HttpMethod.GET == nettyHttpMethod ) {
			  return io.vertx.core.http.HttpMethod.GET;
		  } else if(io.netty.handler.codec.http.HttpMethod.POST == nettyHttpMethod ) {
			  return io.vertx.core.http.HttpMethod.POST;
		  } else if(io.netty.handler.codec.http.HttpMethod.HEAD == nettyHttpMethod ) {
			  return io.vertx.core.http.HttpMethod.HEAD;
		  }else {
			  io.vertx.core.http.HttpMethod method;
		      final String sMethod = nettyHttpMethod.toString();
		      try {
		        method = io.vertx.core.http.HttpMethod.valueOf(sMethod);
		      } catch (IllegalArgumentException e) {
		        method = io.vertx.core.http.HttpMethod.OTHER;
		      }
		    return method;
		  }
	  }
	  
	  public static void main(String[] args) throws Exception {
		  if(true) {
			  System.out.println(toVertHttpMethodNew(METHOD));
		  }
		Options opt = new OptionsBuilder().include(Vertx4httpMethodBenchmark.class.getSimpleName())
				//	.addProfiler("gc")
//					.mode(Mode.Throughput)
//					.warmupIterations(8).measurementIterations(8).forks(2).measurementTime(TimeValue.seconds(1))
//					.warmupTime(TimeValue.seconds(1)).threads(2).timeUnit(TimeUnit.MILLISECONDS)
					.build();
			new Runner(opt).run();
    }
/*
 # JMH version: 1.21
# VM version: JDK 1.8.0_221, Java HotSpot(TM) 64-Bit Server VM, 25.221-b11

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

 Benchmark                               Mode  Cnt    Score    Error   Units  Score/min
Vertx4httpMethodBenchmark.newWay4GET   thrpt   10  982.649 ± 29.455  ops/us      2.231
Vertx4httpMethodBenchmark.newWay4HEAD  thrpt   10  979.842 ± 29.272  ops/us      2.224
Vertx4httpMethodBenchmark.newWay4PUT   thrpt   10  452.905 ± 17.113  ops/us      1.028
Vertx4httpMethodBenchmark.oldWay4GET   thrpt   10  463.152 ± 22.043  ops/us      1.051
Vertx4httpMethodBenchmark.oldWay4HEAD  thrpt   10  443.467 ± 19.277  ops/us      1.007
Vertx4httpMethodBenchmark.oldWay4PUT   thrpt   10  440.537 ± 11.307  ops/us      1.000

 */
}
