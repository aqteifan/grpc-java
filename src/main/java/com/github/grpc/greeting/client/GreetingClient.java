package com.github.grpc.greeting.client;

import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {
    public static void main(String[] args) {
        System.out.println("Hello I'm a gRPC client");
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        //DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);
        //DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(channel);
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);
        //Unary
//        Greeting greeting = Greeting.newBuilder()
//                .setFirstName("Ahmad")
//                .setLastName("Qteifan")
//                .build();
//        GreetRequest greetRequest = GreetRequest.newBuilder()
//                .setGreeting(greeting)
//                .build();
//        GreetResponse greetResponse = greetClient.greet(greetRequest);
//        System.out.println(greetResponse.getResult());

        //server streaming
        GreetManyTimesRequest request = GreetManyTimesRequest.newBuilder()
                .setGreeting(Greeting.newBuilder().setFirstName("Ahmad")).build();
        greetClient.greetManyTimes(request)
                .forEachRemaining(response -> {
                    System.out.println(response.getResult());
                });
        channel.shutdown();

    }
}
