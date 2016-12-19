import base64
import random
import datetime

# FIX params
count_list_gets = 5
count_item_gets = 5
count_item_posts = 5

total_requests = count_list_gets + count_list_gets + count_item_posts

users = { 'Andrew': '123', 'Dmitry': '456', 'Marilyn': 'Monroe'}

hashes = []
for user, password in users.iteritems():
    hashes.append(base64.b64encode(user + ':' + password))

events_with_outcomes = { '1001': [1, 2, 3], '1002': [1, 2, 3, 4], '1003': [1, 2] }

post_body_format = "{ event : %s, outcome : %s, 'value' : %s, 'confirmedOdd' : %s, 'createdTs' : %s }"

for i in xrange(total_requests):
    rand_user_hash = random.choice(hashes)
    rand_req = random.randint(1, total_requests)

    if rand_req <= count_list_gets:
        print "{}||{}||{}||{}".format('GET', '/bets', 'good', rand_user_hash)
    elif rand_req <= count_list_gets + count_item_gets:
        print "{}||{}||{}||{}".format('GET', '/bets/1', 'good', rand_user_hash)
    else:
        rand_event = random.choice(list(events_with_outcomes.iterkeys()))
        rand_outcome = random.choice(events_with_outcomes[rand_event])
        rand_value = random.randint(1, 100)
        rand_odd = str(random.randint(1, 10)) + ':' + str(random.randint(1, 10))
        now = datetime.datetime.now()

        post_body = post_body_format % (rand_event, rand_outcome, rand_value, rand_odd, now.isoformat())
        print "{}||{}||{}||{}||{}".format('POST', '/bets', 'good', rand_user_hash, post_body)
