import { connect, StringCodec } from "nats";

const nc = await connect({ servers: "host.docker.internal:4222"})

console.log(nc)

const sc = StringCodec();

const cb = (err, msg) => {
	if (err) {
		console.log(err)
	} else {
		console.log(sc.decode(msg.data))
	}
}

const sub_sport = nc.subscribe("sports_event", {
		callback: cb
	})
const sub_exec = nc.subscribe("execution", {
		callback: cb
	})


