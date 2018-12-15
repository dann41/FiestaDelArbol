package com.dglapps.fiestadelarbol.services;

import com.dglapps.fiestadelarbol.R;
import com.dglapps.fiestadelarbol.domain.Avatar;

import java.util.HashMap;
import java.util.Map;

public class AvatarService {

    Map<Integer, Avatar> map = new HashMap<>();

    public AvatarService() {
        map.put(R.id.avatar_1, new Avatar(R.id.avatar_1, R.drawable.avatar_1));
        map.put(R.id.avatar_2, new Avatar(R.id.avatar_2, R.drawable.avatar_2));
        map.put(R.id.avatar_3, new Avatar(R.id.avatar_3, R.drawable.avatar_3));
        map.put(R.id.avatar_4, new Avatar(R.id.avatar_4, R.drawable.avatar_4));
        map.put(R.id.avatar_5, new Avatar(R.id.avatar_5, R.drawable.avatar_5));
        map.put(R.id.avatar_6, new Avatar(R.id.avatar_6, R.drawable.avatar_6));
    }

    public Avatar getById(int avatarId) {
        return map.get(avatarId);
    }

}
