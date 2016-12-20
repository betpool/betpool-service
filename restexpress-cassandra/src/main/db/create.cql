drop keyspace if exists betpool_service;
create keyspace betpool_service
with replication = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

use betpool_service;

/*
QUERIES:
-/bets:
 -GET: {header::userid} <- all user bets
 -POST: {header::userid, eventid, outcomeid, value, confirmedodd} <- create a bet
-/bets/betid:
 -GET: {header::userid} <- get info of a particular bet
*/
drop table if exists userbets;
create table userbets (
    userid bigint,
    betid bigint,
    eventid bigint,
    outcomeid bigint,
    value float,
    confirmedodd text,
    createdts timestamp,
    primary key (userid, betid)
);