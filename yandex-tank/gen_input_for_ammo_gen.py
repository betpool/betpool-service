import random
import numpy as np

# FIX params
total_requests = 100000

requests = np.array(["GET_LIST", "GET_ITEM", "CREATE_ITEM"])
req_probabilities = list(np.array([0.35, 0.3, 0.35]))

user_count = 500
events_count = 1000

min_user_id = 100
min_bet_id = 1000


userBets = {}
for i in xrange(user_count):
    userBets[min_user_id + i] = list()

events_with_outcomes = {}

for i in xrange(events_count):
    events_with_outcomes[1000 + i] = list(xrange(1, random.randint(2, 5)))

post_body_format = "{ \"eventId\": \"%s\", \"outcomeId\": \"%s\", \"value\": \"%s\", \"confirmedOdd\": \"%s\" }"

gend_requests = 0
bets_count = 0

cur_user = -1
while gend_requests < total_requests:

    cur_user = (cur_user + 1) % user_count
    cur_user_id = min_user_id + cur_user

    cur_request = np.random.choice(requests, 1, p=req_probabilities)

    if cur_request == "GET_LIST":

        print "{}||{}||{}||{}".format('GET', '/bets', 'get_list', cur_user_id)

    elif cur_request == "GET_ITEM":

        if len(userBets[cur_user_id]) > 0:
            print "{}||{}||{}||{}".format('GET', '/bets/' + str(random.choice(userBets[cur_user_id])), 'get_item', cur_user_id)
        else:
            continue

    else:

        rand_event = random.choice(events_with_outcomes.keys())
        rand_outcome = random.choice(events_with_outcomes[rand_event])

        rand_value = random.randint(1, 100)
        rand_odd = str(random.randint(1, 10)) + ':' + str(random.randint(1, 10))

        post_body = post_body_format % (rand_event, rand_outcome, rand_value, rand_odd)

        userBets[cur_user_id].append(min_bet_id + bets_count)

        print "{}||{}||{}||{}||{}".format('POST', '/bets', 'create_item', cur_user_id, post_body)

        bets_count += 1

    gend_requests += 1

