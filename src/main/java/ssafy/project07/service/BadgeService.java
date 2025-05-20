package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.Badge;
import ssafy.project07.domain.UserBadge;
import ssafy.project07.dto.badge.BadgeResponse;
import ssafy.project07.repository.badge.BadgeRepository;
import ssafy.project07.repository.badge.UserBadgeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final UserBadgeRepository userBadgeRepository;

    public List<BadgeResponse> getAllBadges() {
        return badgeRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public List<BadgeResponse> getUserBadges(Long userId) {
        return userBadgeRepository.findByUserId(userId).stream()
                .map(UserBadge::getBadge)
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private BadgeResponse convertToResponse(Badge badge) {
        BadgeResponse res = new BadgeResponse();
        res.setId(badge.getId());
        res.setName(badge.getName());
        res.setImageUrl(badge.getImageUrl());
        res.setDescription(badge.getDescription());
        return res;
    }
}
