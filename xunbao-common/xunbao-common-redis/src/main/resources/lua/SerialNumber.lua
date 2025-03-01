if(redis.call('exists', KEYS[1]) == 0) then
    redis.call('hset', KEYS[1], ARGV[2], 1);
    return redis.call('hget', KEYS[1], ARGV[2]);
end;

