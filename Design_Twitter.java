class Twitter {
    private static class Tweet implements Comparable<Tweet> {
        private static long count = 0;
        int id;
        long time;
        Tweet(int tweetId) {
            id = tweetId;
            time = count++;
        }

        @Override
        public int compareTo(Tweet o) {
            return Long.compare(o.time, time);
        }
    }

    Map<Integer, LinkedList<Tweet>> tweets;
    Map<Integer, Set<Integer>> following;


    /** Initialize your data structure here. */
    public Twitter() {
        tweets = new HashMap<>();
        following = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        LinkedList<Tweet> list = tweets.computeIfAbsent(userId, k -> new LinkedList<>());
        list.addFirst(new Tweet(tweetId));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> output = new ArrayList<>();
        List<Iterator<Tweet>> iterators = new ArrayList<>();
        List<Tweet> list = tweets.get(userId);
        if (list != null) iterators.add(list.iterator());


        Set<Integer> followees = following.get(userId);
        if (followees != null) {
            for (int followee : followees) {
                list = tweets.get(followee);
                if (list != null) iterators.add(list.iterator());
            }
        }

        PriorityQueue<Tweet> queue = new PriorityQueue<>();
        while (output.size() < 10 && !iterators.isEmpty()) {
            List<Iterator<Tweet>> newIterators = new ArrayList<>();
            for (Iterator<Tweet> iterator: iterators) {
                if (iterator.hasNext()) {
                    queue.add(iterator.next());
                    newIterators.add(iterator);
                }
            }
            iterators = newIterators;
            if (queue.isEmpty()) break;
            output.add(queue.poll().id);
        }
        
        while (output.size() < 10 && !queue.isEmpty())
            output.add(queue.poll().id);
        return output;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        Set<Integer> followees = following.computeIfAbsent(followerId, k -> new HashSet<>());
        followees.add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        Set<Integer> followees = following.computeIfAbsent(followerId, k -> new HashSet<>());
        followees.remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
