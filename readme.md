# Edge project

###
Currently the project is using Postgres and NodeJs to handle the persistence.
Postgresql was chosen due to the incoming data from the message queue being structured data, and I have prior experience using it.
NodeJS is being used due to recent familiarity on a personal project, however there are some considerations here:
	- An system like this for event processing needs to be as fast as possible. NodeJS is a js engine that operates on a non-blocking event loop. For a simple crud app that is IO bound NodeJS is a very reasonable choice, however here we are using protobuf instead of JSON. Looking at some benchmarks, Node doesnt benefit a whole lot from the performance improvements that protobuf brings, unlike some more traditional backend languages that have big gains in processing incoming messages that use protobuf.

So I'm going to look into converting this into using a language that will give better performance. I'm also curious if performance differences at this rate of messaging are even going to be visible	
