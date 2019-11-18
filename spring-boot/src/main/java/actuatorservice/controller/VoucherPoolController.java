package actuatorservice.controller;

import actuatorservice.domain.VoucherCode;
import actuatorservice.service.VoucherPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class VoucherPoolController {

    @Autowired
    private VoucherPoolService voucherPoolService;

    @PutMapping("vouchers/generate")
    @ResponseBody
    public Set<VoucherCode> generateVoucherCodes(@RequestBody GenerateVoucherCodeRequest generateVoucherCodeRequest) {

        return voucherPoolService.generateVoucherCodes(generateVoucherCodeRequest.getOfferName(), generateVoucherCodeRequest.getExpirationDate());

    }

    @GetMapping("vouchers")
    @ResponseBody
    public Set<VoucherCode> getVoucherCodes(@RequestParam final String email, @RequestParam final boolean includeExpired) {

        return voucherPoolService.getVoucherCodes(email, includeExpired);
    }

    @PostMapping("vouchers/{id}/redeem")
    @ResponseBody
    public void redeem (@PathVariable("id") final String uuid) {

        voucherPoolService.redeemVoucherCode(uuid);
    }

}
